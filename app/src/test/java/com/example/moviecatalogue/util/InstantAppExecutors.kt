package com.example.moviecatalogue.util

import java.util.concurrent.Executor

open class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}