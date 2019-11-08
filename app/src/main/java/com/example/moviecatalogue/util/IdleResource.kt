package com.example.moviecatalogue.util

import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.IdlingResource


object IdleResource {
    private const val resource = "RESOURCE"
    private val espressoTestIdlingResource = CountingIdlingResource(resource)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource = espressoTestIdlingResource

}