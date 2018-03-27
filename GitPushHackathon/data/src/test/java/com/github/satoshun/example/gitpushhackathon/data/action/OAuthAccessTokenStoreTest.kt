package com.github.satoshun.example.gitpushhackathon.data.action

import com.github.satoshun.example.gitpushhackathon.data.testutil.oneShotBaseSource
import org.junit.Before
import org.junit.Test

class OAuthAccessTokenStoreTest {
  private val token = OAuthAccessToken("hoge")

  private lateinit var store: OAuthAccessTokenStore

  @Before
  fun setUp() {
    store = OAuthAccessTokenStore(oneShotBaseSource(token))
  }

  @Test
  fun `flow value when will fired OAuthAccessTokenDispatcher`() {
    val test = store.onOAuthAccessToken.test()

    test.assertValue(token)
        .assertNotComplete()
  }
}
