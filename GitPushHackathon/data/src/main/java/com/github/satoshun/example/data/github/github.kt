package com.github.satoshun.example.data.github

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface GitHub {
  @GET("users/whatever")
  fun oauth2(code: String): Deferred<Any>
}
