package com.hexfan.kotlinmvvm.common.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IOTransformer : RxTransformer {

    override fun main() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()

}