package com.github.satoshun.example.gitpushhackathon.oauth.ui

import com.github.satoshun.example.data.github.GitHub
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthCode
import com.github.satoshun.example.gitpushhackathon.oauth.BuildConfig
import kotlinx.coroutines.experimental.async
import javax.inject.Inject
import javax.inject.Singleton

class OAuthActionCreator @Inject constructor(
    private val repository: OAuthRepository,
    private val pageSink: BaseSink<OAuthAction>,
    private val oauthSink: BaseSink<OAuthCode>
) {
  fun getAccessToken(code: String) {
    oauthSink.dispatch(OAuthCode(code))
  }
}


@Singleton
class OAuthRepository @Inject constructor(
    private val gitHub: GitHub
) {
  fun getAccessToken(code: String) = async {
    val response = gitHub.login(
        clientId = BuildConfig.CLIENT_ID,
        clientSecret = BuildConfig.CLIENT_SECRET,
        code = code
    ).await()
  }
}
