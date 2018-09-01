package com.hexfan.kotlinmvvm

import com.hexfan.kotlinmvvm.common.rx.RxTransformer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestTransformer : RxTransformer {
    override fun main() =
            Schedulers.trampoline()

    override fun io(): Scheduler =
            Schedulers.trampoline()

}