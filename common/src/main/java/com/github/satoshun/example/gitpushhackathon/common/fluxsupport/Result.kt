package com.github.satoshun.example.gitpushhackathon.common.fluxsupport

sealed class Result<out T : Any> {
  class Ok<out T : Any> internal constructor(val value: T) : Result<T>()
  class Error internal constructor(val error: Throwable) : Result<Nothing>()

  companion object {
    fun <T : Any> ok(value: T) = Ok(value)
    fun error(e: Throwable) = Error(e)
  }
}
