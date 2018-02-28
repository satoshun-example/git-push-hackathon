package com.github.satoshun.example.gitpushhackathon.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginActivityModule {
  @ContributesAndroidInjector
  fun contributeLoginActivity(): LoginActivity
}
