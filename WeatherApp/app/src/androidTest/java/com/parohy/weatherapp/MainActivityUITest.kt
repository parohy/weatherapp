package com.parohy.weatherapp

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.parohy.weatherapp.ui.activity.MainActivity
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
    private lateinit var invalidInput4: String
    private lateinit var validInput: String

    private lateinit var errorMessage: String
    private lateinit var context: Context

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun initStrings() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        invalidInput1 = "K0sic€"
        invalidInput2 = "€verEst"
        invalidInput3 = "@mazon"
        invalidInput4 = "Par1s"
        validInput = "new york"
        errorMessage = context.getString(R.string.invalid_input)
    }

    /** ERROR **/
    @Test
    fun testError1() {
        onView(withId(R.id.submitField)).perform(replaceText(invalidInput1))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.inputError)).check(matches(withText(errorMessage)))
    }

    @Test
    fun testError2() {
        onView(withId(R.id.submitField)).perform(replaceText(invalidInput2))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.inputError)).check(matches(withText(errorMessage)))
    }

    @Test
    fun testError3() {
        onView(withId(R.id.submitField)).perform(replaceText(invalidInput3))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.inputError)).check(matches(withText(errorMessage)))
    }

    @Test
    fun testError4() {
        onView(withId(R.id.submitField)).perform(replaceText(invalidInput4))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.inputError)).check(matches(withText(errorMessage)))
    }
    /******/

    /** VALID **/
    @Test
    fun testValid() {
        onView(withId(R.id.submitField)).perform(typeText(validInput))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.inputError)).check(matches(withText("")))
    }
}
