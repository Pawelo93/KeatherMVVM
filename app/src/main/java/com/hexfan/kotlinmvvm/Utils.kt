package com.hexfan.kotlinmvvm

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by Pawel on 09.02.2018.
 */

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, body: (newData: T) -> Unit){
    observe(lifecycleOwner, Observer {
        newData ->
        if(newData != null)
            body(newData)
    })
}