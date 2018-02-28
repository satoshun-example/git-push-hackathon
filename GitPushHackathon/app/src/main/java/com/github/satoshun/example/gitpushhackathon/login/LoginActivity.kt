package com.github.satoshun.example.gitpushhackathon.login

import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.view.View
import androidx.net.toUri
import com.github.satoshun.example.gitpushhackathon.R
import dagger.android.support.DaggerAppCompatActivity


class LoginActivity : DaggerAppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_act)

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
