package com.github.satoshun.example.data.github

import kotlinx.coroutines.experimental.Deferred
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHub {
  /**
   * https://developer.github.com/apps/building-oauth-apps/authorization-options-for-oauth-apps/#2-users-are-redirected-back-to-your-site-by-github
   */
  @Headers("Accept: application/json")
  @POST("login/oauth/access_token")
  fun login(
      @Query("client_id") clientId: String,
      @Query("client_secret") clientSecret: String,
      @Query("code") code: String
  ): Deferred<AccessTokenResponse>
}

@Serializable
data class AccessTokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("scope") val scope: String,
    @SerialName("token_type") val tokenType: String
)


interface GitHubAPI {
  @Headers("Accept: application/vnd.github.v3+json")
  @GET("issues")
  fun issues(
      @Query("access_token") accessToken: String // todo use type safe class, not String
  ): Deferred<String>
}
