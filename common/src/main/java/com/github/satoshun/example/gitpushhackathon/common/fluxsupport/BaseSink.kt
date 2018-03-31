package com.github.satoshun.example.gitpushhackathon.common.fluxsupport

interface BaseSink<T> {
  fun dispatch(action: T)
}
