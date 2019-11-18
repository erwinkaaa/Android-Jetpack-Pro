package com.example.moviecatalogue.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.util.IdleResource
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val data = 5

    private val favMovieFragment = 0
    private val favTvFragment = 1

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdleResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdleResource.getEspressoIdlingResource())
    }

    @Test
    fun testAllBehaviours() {
        Behaviour.toMovieFragment()
        for (i in 0 .. data) {
            Behaviour.openItem(i)
            Behaviour.checkDisplayDetail()
            Behaviour.addToFavorite(activityTestRule)
        }
        Behaviour.toTVShowFragment()
        for (i in 0 .. data) {
            Behaviour.openItem(i)
            Behaviour.checkDisplayDetail()
            Behaviour.addToFavorite(activityTestRule)
        }
        Behaviour.toFavoriteFragment()
        // IN FAV MOVIE FRAGMENT
        for (i in 0 .. data) {
            Behaviour.openItemRecyclerIndex(0, favMovieFragment)
            Behaviour.checkDisplayDetail()
            Behaviour.removeFromFavorite(activityTestRule)
        }
        // IN FAV TV FRAGMENT
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        Thread.sleep(1000)
        for (i in 0 .. data) {
            Behaviour.openItemRecyclerIndex(0, favTvFragment)
            Behaviour.checkDisplayDetail()
            Behaviour.removeFromFavorite(activityTestRule)
        }
    }

    object Behaviour {
        fun toMovieFragment() {
            onView(withId(R.id.bottom_layout)).check(matches(isDisplayed()))
            onView(withId(R.id.movies)).perform(click())
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        }

        fun toTVShowFragment() {
            onView(withId(R.id.bottom_layout)).check(matches(isDisplayed()))
            onView(withId(R.id.tvshows)).perform(click())
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        }

        fun openItem(position: Int) {
            recyclerViewClick(R.id.recyclerView, position)
        }

        fun checkDisplayDetail() {
            onView(withText(R.string.detail_film)).check(matches(isDisplayed()))
            onView(withId(R.id.state_favorite)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        }

        fun addToFavorite(activityTestRule: ActivityTestRule<MainActivity>) {
            onView(withId(R.id.state_favorite)).perform(click())
            onView(withText(R.string.added_to_favorite))
                .inRoot(withDecorView(not(activityTestRule.activity.window.decorView))).check(matches(isDisplayed()))
            pressBack()
        }

        fun toFavoriteFragment() {
            onView(withId(R.id.bottom_layout)).check(matches(isDisplayed()))
            onView(withId(R.id.favorite)).perform(click())
            onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
            onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        }

        fun openItemRecyclerIndex(position: Int, index: Int) {
            withIndexRecyclerViewClick(R.id.recyclerView, position, index)
        }

        fun removeFromFavorite(activityTestRule: ActivityTestRule<MainActivity>) {
            onView(withId(R.id.state_favorite)).perform(click())
            onView(withText(R.string.removed_from_favorite))
                .inRoot(withDecorView(not(activityTestRule.activity.window.decorView))).check(matches(isDisplayed()))
            pressBack()
        }

        private fun recyclerViewClick(recyclerViewId: Int, position: Int) {
            onView(withId(recyclerViewId))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        position,
                        click()
                    )
                )
        }

        private fun withIndexRecyclerViewClick(recyclerViewId: Int, position: Int, index: Int) {
            onView(withIndex(withId(recyclerViewId), index))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        position,
                        click()
                    )
                )
        }

        private fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                var currentIndex = 0

                override fun describeTo(description: Description) {
                    description.appendText("with index: ")
                    description.appendValue(index)
                    matcher.describeTo(description)
                }

                override fun matchesSafely(view: View): Boolean {
                    return matcher.matches(view) && currentIndex++ == index
                }
            }
        }
    }

}