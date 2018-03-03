package com.github.satoshun.example.gitpushhackathon.ui.fluxsupport

import io.reactivex.Flowable

interface BaseSource<T> {
  val actions: Flowable<T>
}
