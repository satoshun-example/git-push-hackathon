package com.github.satoshun.example.gitpushhackathon.data.action

import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseDispatcher
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import dagger.Binds
import dagger.Module
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject
import javax.inject.Singleton

@Module
interface OAuthAccessTokenModule {
  @Binds fun bindSource(dispatcher: OAuthAccessTokenDispatcher): BaseSource<OAuthAccessToken>
  @Binds fun bindSink(dispatcher: OAuthAccessTokenDispatcher): BaseSink<OAuthAccessToken>
}

data class OAuthAccessToken(val token: String)

@Singleton
class OAuthAccessTokenDispatcher @Inject constructor() : BaseDispatcher<OAuthAccessToken> {
  override val actions = BehaviorProcessor.create<OAuthAccessToken>()!!

  override fun dispatch(action: OAuthAccessToken) {
    actions.onNext(action)
  }
}

@Singleton
class OAuthAccessTokenStore @Inject constructor(
    source: BaseSource<OAuthAccessToken>
) {
  val onOAuthAccessToken = source.actions
}
