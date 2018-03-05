package com.github.satoshun.example.gitpushhackathon.oauth.ui

import com.github.satoshun.example.gitpushhackathon.common.di.PerActivity
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseDispatcher
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.Result
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

data class OAuthAction(val result: Result<Unit>)

@PerActivity
class OAuthActionDispatcher @Inject constructor() : BaseDispatcher<OAuthAction> {
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
      .map { it.result }
      .ofType(Result.Ok::class.java)

  val showFailedDialog = source.actions
      .map { it.result }
      .ofType(Result.Error::class.java)
}
