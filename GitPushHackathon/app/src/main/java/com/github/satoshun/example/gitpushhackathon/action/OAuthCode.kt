package com.github.satoshun.example.gitpushhackathon.action

import com.github.satoshun.example.gitpushhackathon.ui.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.ui.fluxsupport.BaseSource
import dagger.Binds
import dagger.Module
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject
import javax.inject.Singleton

// todo rename

@Module
interface OAuthCodeModule {
  @Binds fun bindSource(dispatcher: OAuthCodeDispatcher): BaseSource<OAuthCode>
  @Binds fun bindSink(dispatcher: OAuthCodeDispatcher): BaseSink<OAuthCode>
}

data class OAuthCode(val code: String)

@Singleton
class OAuthCodeDispatcher @Inject constructor() :
    BaseSource<OAuthCode>,
    BaseSink<OAuthCode> {
  override val actions = BehaviorProcessor.create<OAuthCode>()!!

  override fun dispatch(action: OAuthCode) {
    actions.onNext(action)
  }
}

@Singleton
class OAuthCodeStore @Inject constructor(
    source: BaseSource<OAuthCode>
) {
  val onOAuthCode = source.actions
}
