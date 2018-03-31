package com.github.satoshun.example.gitpushhackathon.data.testutil

import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor


internal class BaseSourceImpl<T>(override val actions: Flowable<T>) : BaseSource<T>

fun <T> oneShotBaseSource(t: T): BaseSource<T> {
  return BaseSourceImpl(BehaviorProcessor.createDefault(t))
}

fun <T> emptyBaseSource(): BaseSource<T> {
  return BaseSourceImpl(Flowable.empty())
}
