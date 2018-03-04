package com.github.satoshun.example.gitpushhackathon.oauth.ui

import com.github.satoshun.example.data.github.AccessTokenResponse
import com.github.satoshun.example.data.github.GitHub
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.Result
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthAccessToken
import com.github.satoshun.example.gitpushhackathon.oauth.BuildConfig
import kotlinx.coroutines.experimental.async
import javax.inject.Inject
import javax.inject.Singleton

class OAuthActionCreator @Inject constructor(
    private val repository: OAuthRepository,
    private val pageSink: BaseSink<OAuthAction>,
    private val tokenSink: BaseSink<OAuthAccessToken>
) {
  fun getAccessToken(code: String) {
    async {
      try {
        val response = repository.getAccessToken(code)
        pageSink.dispatch(OAuthAction(Result.ok(Unit)))
        tokenSink.dispatch(OAuthAccessToken(response.accessToken))
      } catch (e: Throwable) {
        pageSink.dispatch(OAuthAction(Result.error(e)))
      }
    }
  }
}


@Singleton
class OAuthRepository @Inject constructor(
    private val gitHub: GitHub
) {
  suspend fun getAccessToken(code: String): AccessTokenResponse {
    return gitHub.login(
        clientId = BuildConfig.CLIENT_ID,
        clientSecret = BuildConfig.CLIENT_SECRET,
        code = code
    ).await()
  }
}
