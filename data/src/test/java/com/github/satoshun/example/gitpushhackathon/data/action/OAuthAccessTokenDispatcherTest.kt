package com.github.satoshun.example.gitpushhackathon.data.action

import org.junit.Before
import org.junit.Test

class OAuthAccessTokenDispatcherTest {
  private lateinit var dispatcher: OAuthAccessTokenDispatcher

  @Before
  fun setUp() {
    dispatcher = OAuthAccessTokenDispatcher()
  }

  @Test
  fun dispatch() {
    val token = OAuthAccessToken("token")
    val test = dispatcher.actions.test()

    dispatcher.dispatch(token)

    test.assertValue(token)
        .assertNotComplete()
  }
}
