package com.github.satoshun.example.gitpushhackathon.data.testutil

import com.github.satoshun.example.gitpushhackathon.data.action.OAuthAccessTokenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [OAuthAccessTokenModule::class])
internal interface TestAppComponent
