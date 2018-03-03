package com.github.satoshun.example.gitpushhackathon.ui.fluxsupport

sealed class Result<T> {
  class Ok<T>(val value: T) : Result<T>()
  class Error(val error: Throwable) : Result<Nothing>()

  companion object {
    fun <T> ok(value: T) = Ok(value)
    fun error(e: Throwable) = Error(e)
  }
}
