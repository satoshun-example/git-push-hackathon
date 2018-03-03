package com.github.satoshun.example.data.github

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.stringBased
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.JSON
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
  @Provides @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    // todo use cache
    return OkHttpClient.Builder()
        .build()
  }

  @Provides @Singleton
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    val contentType = MediaType.parse("application/json")!!
    val json = JSON
    return Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(stringBased(contentType, json::parse, json::stringify))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
  }

  @Provides @Singleton
  fun provideGitHub(retrofit: Retrofit): GitHub {
    return retrofit.create(GitHub::class.java)
  }
}
