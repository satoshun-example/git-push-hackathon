package com.github.satoshun.example.gitpushhackathon.ui.feed

import android.os.Bundle
import android.util.Log
import com.github.satoshun.example.data.github.GitHubAPI
import com.github.satoshun.example.gitpushhackathon.R
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseDispatcher
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthAccessToken
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthAccessTokenStore
import com.github.satoshun.io.reactivex.lifecycleowner.subscribeOf
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.ofType
import kotlinx.coroutines.experimental.async
import javax.inject.Inject
import javax.inject.Singleton

class FeedActivity : DaggerAppCompatActivity() {
  @Inject lateinit var creator: FeedActionCreator
  @Inject lateinit var store: FeedStore
  @Inject lateinit var oauthStore: OAuthAccessTokenStore

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.feed_act)

    watchStore()

    oauthStore.onOAuthAccessToken.firstOrError()
        .subscribeOf(this, onSuccess = creator::newFeeds)
  }

  private fun watchStore() {
    store.newFeeds
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
    store.errorFeed
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
  }
}

class FeedActionCreator @Inject constructor(
    private val apiRepository: GitHubAPIRepository,
    private val sink: BaseSink<FeedAction>
) {
  fun newFeeds(token: OAuthAccessToken) {
    async {
      try {
        val data = apiRepository.getIssues(token)
        Log.d("data", data.toString())
      } catch (e: Exception) {
        Log.e("data", e.toString())
      }
    }
  }
}

@Singleton
class GitHubAPIRepository @Inject constructor(
    private val gitHub: GitHubAPI
) {
  suspend fun getIssues(accessToken: OAuthAccessToken): Any {
    return gitHub.issues(accessToken = accessToken.token).await()
  }
}

typealias FeedEntryList = FeedAction.FeedEntryList
typealias FeedError = FeedAction.FeedError

sealed class FeedAction {
  data class FeedEntryList(val titles: List<String>) : FeedAction()
  data class FeedError(val error: Throwable) : FeedAction()
}

class FeedDispatcher @Inject constructor() : BaseDispatcher<FeedAction> {
  override val actions = PublishProcessor.create<FeedAction>()!!

  override fun dispatch(action: FeedAction) {
    actions.onNext(action)
  }
}

class FeedStore @Inject constructor(
    source: BaseSource<FeedAction>
) {
  val newFeeds: Flowable<FeedEntryList> = source.actions.ofType()
  val errorFeed: Flowable<FeedError> = source.actions.ofType()
}
