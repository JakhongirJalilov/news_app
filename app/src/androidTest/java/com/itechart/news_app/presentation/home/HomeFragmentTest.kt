package com.itechart.news_app.presentation.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.itechart.news_app.R
import com.itechart.news_app.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun view(){
//        val recycler: ViewInteraction = onView(withId(R.id.listNews))
        onView(withId(R.id.listNews)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}