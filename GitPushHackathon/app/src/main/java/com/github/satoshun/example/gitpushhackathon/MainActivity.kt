package com.github.satoshun.example.gitpushhackathon

import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.net.toUri


class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    findViewById<View>(R.id.login).setOnClickListener {
      loginGitHub()
    }
  }

  private fun loginGitHub() {
    val uri = "https://github.com/login/oauth/authorize?client_id=id".toUri()
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(this, uri)
  }
}
