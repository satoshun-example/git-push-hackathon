package com.github.satoshun.example.gitpushhackathon.ui.feed

import android.os.Bundle
import com.github.satoshun.example.gitpushhackathon.R
import com.github.satoshun.example.gitpushhackathon.ui.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.ui.fluxsupport.BaseSource
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class FeedActivity : DaggerAppCompatActivity() {
  @Inject lateinit var creator: FeedActionCreator
  @Inject lateinit var store: FeedStore

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.feed_act)

    store.newFeeds.observeOn(AndroidSchedulers.mainThread())
    store.errorFeed.observeOn(AndroidSchedulers.mainThread())

    creator.newFeeds()
  }
}

class FeedActionCreator @Inject constructor(
    private val sink: BaseSink<FeedAction>
) {
  fun newFeeds() {
  }
}

typealias FeedEntryList = FeedAction.FeedEntryList
typealias FeedError = FeedAction.FeedError

sealed class FeedAction {
  data class FeedEntryList(val titles: List<String>) : FeedAction()
  data class FeedError(val error: Throwable) : FeedAction()
}

class FeedDispatcher @Inject constructor() :
    BaseSource<FeedAction>,
    BaseSink<FeedAction> {
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
