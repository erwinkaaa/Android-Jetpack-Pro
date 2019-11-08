package com.example.moviecatalogue.util

import com.example.moviecatalogue.repository.remote.RemoteRepository
import com.example.moviecatalogue.repository.main.MainRepository


object Injection {
    fun provideRepository(): MainRepository {
        val remoteRepository = RemoteRepository()
        return MainRepository(remoteRepository)
    }
}