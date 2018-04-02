package com.github.satoshun.example.gitpushhackathon.ui.login

import android.support.v7.app.AppCompatActivity
import com.github.satoshun.example.gitpushhackathon.common.di.PerActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginActivityModule {
  @PerActivity
  @ContributesAndroidInjector(modules = [LoginActivityBindModule::class])
  fun contributeLoginActivity(): LoginActivity
}


@Module
interface LoginActivityBindModule {
  @Binds
  fun bindAppCompatActivity(activity: LoginActivity): AppCompatActivity
}
