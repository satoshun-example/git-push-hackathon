package com.github.satoshun.example.data.github

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.stringBased
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.JSON
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [
  GitHubModule::class,
  GitHubAPIModule::class
])
class ApiModule {
  @Provides @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BASIC
    }

    // todo use cache
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
  }

  @Provides
  fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
    val contentType = MediaType.parse("application/json")!!
    val json = JSON
    return Retrofit.Builder()
        .addConverterFactory(stringBased(contentType, json::parse, json::stringify))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
  }
}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class GitHubTag

@Module
class GitHubModule {
  @Provides @GitHubTag
  fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
    return builder
        .baseUrl("https://github.com")
        .build()
  }

  @Provides @Singleton
  fun provideGitHub(@GitHubTag retrofit: Retrofit): GitHub {
    return retrofit.create(GitHub::class.java)
  }
}


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class GitHubAPITag

@Module
class GitHubAPIModule {
  @Provides @GitHubAPITag
  fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
    return builder
        .baseUrl("https://api.github.com")
        .build()
  }

  @Provides @Singleton
  fun provideGitHub(@GitHubAPITag retrofit: Retrofit): GitHubAPI {
    return retrofit.create(GitHubAPI::class.java)
  }
}
