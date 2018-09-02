package com.hexfan.kotlinmvvm.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, body: (newData: T) -> Unit) {
    observe(lifecycleOwner, Observer { newData ->
        if (newData != null)
            body(newData)
    })
}