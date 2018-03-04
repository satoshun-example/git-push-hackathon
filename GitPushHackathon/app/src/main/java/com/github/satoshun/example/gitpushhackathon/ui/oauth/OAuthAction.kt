package com.github.satoshun.example.gitpushhackathon.ui.oauth

import com.github.satoshun.example.gitpushhackathon.PerActivity
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

typealias OAuthSuccessAction = OAuthAction.OAuthSuccessAction

sealed class OAuthAction {
  object OAuthSuccessAction : OAuthAction()
}

@PerActivity
class OAuthActionDispatcher @Inject constructor() :
    BaseSource<OAuthAction>,
    BaseSink<OAuthAction> {
  override val actions = PublishProcessor.create<OAuthAction>()!!

  override fun dispatch(action: OAuthAction) {
    actions.onNext(action)
  }
}

@PerActivity
class OAuthActionStore @Inject constructor(
    source: BaseSource<OAuthAction>
) {
  val onFinishPage = source.actions
}
