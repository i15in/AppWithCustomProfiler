package com.example.podlodkaandroidcrewsampleproject

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.repeatedlyUntil
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager2.widget.ViewPager2
import com.example.podlodkaandroidcrewsampleproject.viewpager.Config
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PerformanceInstrumentedTest {
	
	@get:Rule var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
	
	@Test
	fun testPerformance() {
		onView(withId(R.id.viewPager2)).perform(repeatedlyUntil(swipe(), matcher(), Config.ITEM_COUNT * 2))
	}
	
	private fun swipe(): ViewAction {
		return GeneralSwipeAction(
			Swipe.FAST, GeneralLocation.CENTER_RIGHT, GeneralLocation.CENTER_LEFT, Press.FINGER
		)
	}
	
	private fun matcher(): TypeSafeMatcher<View> {
		return object : TypeSafeMatcher<View>() {
			override fun describeTo(description: Description) {
				description.appendText("Need swipe to last element of gallery")
			}
			
			override fun matchesSafely(item: View): Boolean {
				val viewPager2 = item as ViewPager2
				return viewPager2.currentItem == Config.ITEM_COUNT - 1
			}
		}
	}
}