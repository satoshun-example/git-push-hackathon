package com.github.satoshun.example.gitpushhackathon

import com.github.satoshun.example.data.github.ApiModule
import com.github.satoshun.example.gitpushhackathon.action.OAuthCodeModule
import com.github.satoshun.example.gitpushhackathon.ui.feed.FeedActivityModule
import com.github.satoshun.example.gitpushhackathon.ui.login.LoginActivityModule
import com.github.satoshun.example.gitpushhackathon.ui.oauth.OAuthActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ApiModule::class,
  AppDispatcherModule::class,
  LoginActivityModule::class,
  FeedActivityModule::class,
  OAuthActivityModule::class
])
interface AppComponent : AndroidInjector<App> {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: App): Builder

    fun build(): AppComponent
  }
}

@Module(includes = [
  OAuthCodeModule::class
])
interface AppDispatcherModule


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment
