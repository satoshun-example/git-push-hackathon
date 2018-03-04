package com.github.satoshun.example.gitpushhackathon.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.satoshun.example.gitpushhackathon.R
import com.github.satoshun.example.gitpushhackathon.common.di.PerActivity
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthCodeStore
import com.github.satoshun.example.gitpushhackathon.oauth.ui.OAuthActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

  @Inject lateinit var oauthStore: OAuthCodeStore

  @Inject lateinit var navigator: LoginNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_act)
    findViewById<View>(R.id.login).setOnClickListener {
      navigator.oauthLogin()
    }

    watchStore()
  }

  private fun watchStore() {
  }
}

@PerActivity
class LoginNavigator @Inject constructor(
    private val activity: AppCompatActivity
) {
  fun oauthLogin() {
    val intent = Intent(activity, OAuthActivity::class.java)
    activity.startActivity(intent)
  }
}
