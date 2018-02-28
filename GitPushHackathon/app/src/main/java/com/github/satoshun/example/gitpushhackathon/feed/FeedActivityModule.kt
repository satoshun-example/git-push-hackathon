package com.github.satoshun.example.gitpushhackathon.feed

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeedActivityModule {
  @ContributesAndroidInjector
  fun contributeFeedActivity(): FeedActivity
}
