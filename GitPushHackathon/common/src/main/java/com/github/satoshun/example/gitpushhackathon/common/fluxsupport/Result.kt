package com.github.satoshun.example.gitpushhackathon.common.fluxsupport

sealed class Result<T> {
  class Ok<T> internal constructor(val value: T) : Result<T>()
  class Error internal constructor(val error: Throwable) : Result<Nothing>()

  companion object {
    fun <T> ok(value: T) = Ok(value)
    fun error(e: Throwable) = Error(e)
  }
}
