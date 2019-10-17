package com.example.moviecatalogue.detail

import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.utils.FakeDataDummy
import org.junit.Rule
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviecatalogue.R
import org.junit.Test


class DetailActivityTest {

    private val dummyMovie = FakeDataDummy().generateMovieData()[0]

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<DetailActivity> = object :
        ActivityTestRule<DetailActivity>(DetailActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(DetailActivity.DATA_EXTRA, dummyMovie)
            return result
        }
    }

    @Test
    fun detailActivity() {
        onView(withText(R.string.detail_film)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_releasedate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_durationtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_distributedby)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.iv_poster)).check(matches(withContentDescription(R.string.img_desc)))
        onView(withId(R.id.tv_releasedate)).check(matches(withText(dummyMovie.releaseDate)))
        onView(withId(R.id.tv_durationtime)).check(matches(withText(dummyMovie.runningTime)))
        onView(withId(R.id.tv_distributedby)).check(matches(withText(dummyMovie.distributedBy)))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyMovie.description)))
    }

}