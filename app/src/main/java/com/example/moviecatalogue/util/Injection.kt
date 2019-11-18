package com.example.moviecatalogue.util

import android.app.Application
import com.example.moviecatalogue.repository.local.LocalRepository
import com.example.moviecatalogue.repository.main.MainRepository
import com.example.moviecatalogue.repository.remote.RemoteRepository


object Injection {
    fun provideRepository(application: Application): MainRepository {
        val localRepository = LocalRepository(application)
        val remoteRepository = RemoteRepository()
        val appExecutors = AppExecutors()
        return MainRepository(remoteRepository, localRepository, appExecutors)
    }
}