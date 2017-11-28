package com.hexfan.kotlinmvvm.ui.main

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
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

/**
 * Created by Paweł Antonik on 28.11.2017.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, true, false)

    lateinit var viewModel: MainViewModel
    lateinit var data: MutableLiveData<String>

    @Before
    fun setup(){
        data = MutableLiveData()
        viewModel = MainViewModelMock(data)
        setupInjection()
        activityRule.launchActivity(Intent())
    }

    @After
    fun clean(){
        activityRule.finishActivity()
    }

    @Test
    fun showText(){
        data.postValue("Hello")

        onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.withText("Hello")))
    }

    private fun setupInjection(){
        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as? TestApplication)?.injector = generateInjector()
    }

    private fun generateInjector() = AndroidInjector<Activity> {
        (it as? MainActivity)?.viewModelFactory = MainViewModelMock.Factory(viewModel)
    }

    private class MainViewModelMock(var data: MutableLiveData<String>) : MainViewModel() {

        override fun getForecast(): LiveData<String> {
            return data
        }

        class Factory (val viewModel: MainViewModel) : MainViewModel.Factory(){

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModel as T
            }
        }
    }


}