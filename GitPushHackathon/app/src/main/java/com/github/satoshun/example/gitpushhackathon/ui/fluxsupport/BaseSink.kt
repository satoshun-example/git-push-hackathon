package com.github.satoshun.example.gitpushhackathon.ui.fluxsupport

interface BaseSink<T> {
  fun dispatch(action: T)
}
