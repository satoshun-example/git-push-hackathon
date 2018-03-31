package com.github.satoshun.example.gitpushhackathon.data.action

import com.github.satoshun.example.gitpushhackathon.common.fluxsupport.BaseSource
import com.github.satoshun.example.gitpushhackathon.data.testutil.TestAppComponent
import com.github.satoshun.example.gitpushhackathon.data.testutil.oneShotBaseSource
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

val mockToken = OAuthAccessToken("hoge")

@Singleton
@Component(modules = [
  OAuthAccessTokenDispatcherOverrideModule::class
])
internal interface StoreTestAppComponent : TestAppComponent {
  fun inject(test: OAuthAccessTokenStoreTest)
}

@Module
class OAuthAccessTokenDispatcherOverrideModule {
  @Provides
  fun provideBaseSource(): BaseSource<OAuthAccessToken> {
    return oneShotBaseSource(mockToken)
  }
}
