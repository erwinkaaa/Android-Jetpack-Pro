package com.example.moviecatalogue.repository.main

import com.example.moviecatalogue.repository.remote.ApiResponse
import androidx.lifecycle.LiveData
import com.example.moviecatalogue.util.AppExecutors
import androidx.lifecycle.MediatorLiveData
import com.example.moviecatalogue.repository.vo.Resource
import com.example.moviecatalogue.repository.remote.StatusResponse


abstract class NetworkBoundResource<ResultType, RequestType>() {

    private var result = MediatorLiveData<Resource<ResultType>>()

    private var mExecutors: AppExecutors? = null

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    constructor(appExecutors: AppExecutors) : this() {
        this.mExecutors = appExecutors
        result.value = Resource.loading(null)

        val dbSource = this.loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (this.shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                StatusResponse.SUCCESS -> mExecutors?.diskIO()?.execute {

                    saveCallResult(response.body!!)

                    mExecutors?.mainThread()?.execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.setValue(Resource.success(newData))
                        }
                    }

                }

                StatusResponse.EMPTY -> mExecutors?.mainThread()?.execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.setValue(Resource.success(newData))
                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.setValue(Resource.error(response.message, newData))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

}