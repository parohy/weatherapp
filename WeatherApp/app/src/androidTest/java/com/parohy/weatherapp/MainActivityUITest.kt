package com.parohy.weatherapp

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.parohy.weatherapp.ui.activity.MainActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityUITest {
    private lateinit var invalidInput1: String
    private lateinit var invalidInput2: String
    private lateinit var invalidInput3: String
    private lateinit var validInput: String

    private lateinit var errorMessage: String
    private lateinit var context: Context

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun initStrings() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        invalidInput1 = "K0sic€"
        invalidInput2 = "123"
        invalidInput3 = "los-angels"
        validInput = "pino sulla sponda del lago maggiore"
        errorMessage = context.getString(R.string.invalid_input)
    }

    /** ERROR **/
    @Test
    fun testError1() {
        onView(allOf(withId(R.id.searchField))).perform(replaceText(invalidInput1))
        onView(allOf(withId(R.id.submitButton))).perform(click())
        onView(allOf(withId(R.id.searchField))).check(matches(hasErrorText(errorMessage)))
    }
    @Test
    fun testError2() {
        onView(allOf(withId(R.id.searchField))).perform(replaceText(invalidInput2))
        onView(allOf(withId(R.id.submitButton))).perform(click())
        onView(allOf(withId(R.id.searchField))).check(matches(hasErrorText(errorMessage)))
    }
    @Test
    fun testError3() {
        onView(allOf(withId(R.id.searchField))).perform(replaceText(invalidInput3))
        onView(allOf(withId(R.id.submitButton))).perform(click())
        onView(allOf(withId(R.id.searchField))).check(matches(hasErrorText(errorMessage)))
    }

    /******/

    /** VALID **/
    @Test
    fun testValid() {
        onView(allOf(withId(R.id.searchField))).perform(typeText(validInput))
        onView(allOf(withId(R.id.submitButton))).perform(click())
        onView(allOf(withId(R.id.searchField))).check(matches(not(hasErrorText(errorMessage))))
    }
}
