package com.hexfan.kotlinmvvm.ui.main

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hexfan.kotlinmvvm.R
import com.hexfan.kotlinmvvm.TestApplication
import dagger.android.AndroidInjector
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    @Mock
    lateinit var viewModel: MainViewModel
    val data = MutableLiveData<String>()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        TestApplication.setInjector{
            (it as? MainActivity)?.viewModel = viewModel
        }

        Mockito.`when`(viewModel.getForecast()).thenReturn(data)
        activityRule.launchActivity(Intent())
    }

    @After
    fun clean(){
        activityRule.finishActivity()
    }

    @Test
    fun showText(){
        data.postValue("Hello")
        onView(withId(R.id.textView)).check(matches(withText("Hello")))
    }
}