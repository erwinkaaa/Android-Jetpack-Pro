package com.example.moviecatalogue.ui.detail

import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviecatalogue.R
import com.example.moviecatalogue.util.IdleResource
import org.junit.After
import org.junit.Before
import org.junit.Test


class DetailActivityTest {

    private val idMovie = "475557"

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<DetailActivity> = object :
        ActivityTestRule<DetailActivity>(DetailActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.ID_EXTRA, idMovie)
            result.putExtra(DetailActivity.TYPE_EXTRA, DetailActivity.MOVIE)
            return result
        }
    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdleResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdleResource.getEspressoIdlingResource())
    }

    @Test
    fun movieDetailActivity() {
        onView(withText(R.string.detail_film)).check(matches(isDisplayed()))
        onView(withId(R.id.layout_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_releasedate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_durationtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_distributedby)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }

}