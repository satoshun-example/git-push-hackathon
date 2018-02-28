package com.github.satoshun.example.gitpushhackathon.feed

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
