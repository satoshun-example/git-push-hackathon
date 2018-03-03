package com.github.satoshun.example.gitpushhackathon.ui.oauth

import com.github.satoshun.example.data.github.GitHub
import com.github.satoshun.example.gitpushhackathon.action.OAuthCode
import com.github.satoshun.example.gitpushhackathon.ui.fluxsupport.BaseSink
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
  fun getAccessToken(code: String) {
  }
}
