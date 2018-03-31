package com.github.satoshun.example.gitpushhackathon.ui.feed

import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeedActivityModule {
  @ContributesAndroidInjector(modules = [
    FeedActivityBindModule::class
  ])
  fun contributeFeedActivity(): FeedActivity
}

@Module
interface FeedActivityBindModule {
  @Binds fun bindSource(dispatcher: FeedDispatcher): BaseSource<FeedAction>
  @Binds fun bindSink(dispatcher: FeedDispatcher): BaseSink<FeedAction>
}
