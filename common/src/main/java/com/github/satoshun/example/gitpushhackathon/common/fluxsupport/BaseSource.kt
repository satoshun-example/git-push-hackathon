package com.github.satoshun.example.gitpushhackathon.common.fluxsupport

import io.reactivex.Flowable

interface BaseSource<T> {
  val actions: Flowable<T>
}
