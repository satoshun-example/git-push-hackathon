package com.github.satoshun.example.gitpushhackathon

import com.github.satoshun.example.gitpushhackathon.feed.FeedActivityModule
import com.github.satoshun.example.gitpushhackathon.login.LoginActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  LoginActivityModule::class,
  FeedActivityModule::class
])
interface AppComponent : AndroidInjector<App> {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: App): Builder

    fun build(): AppComponent
  }
}
