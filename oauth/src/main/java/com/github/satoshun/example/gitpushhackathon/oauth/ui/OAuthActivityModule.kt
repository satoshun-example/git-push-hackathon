package com.github.satoshun.example.gitpushhackathon.oauth.ui

import com.github.satoshun.example.gitpushhackathon.common.di.PerActivity
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSink
import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OAuthActivityModule {
  @PerActivity
  @ContributesAndroidInjector(modules = [OAuthActionModule::class])
  fun contributerOAuthActivity(): OAuthActivity
}

@Module
interface OAuthActionModule {
  @Binds fun bindSource(dispatcher: OAuthActionDispatcher): BaseSource<OAuthAction>
  @Binds fun bindSink(dispatcher: OAuthActionDispatcher): BaseSink<OAuthAction>
}
