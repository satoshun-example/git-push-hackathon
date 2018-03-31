package com.github.satoshun.example.gitpushhackathon.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.satoshun.example.gitpushhackathon.R
import com.github.satoshun.example.gitpushhackathon.common.di.PerActivity
import com.github.satoshun.example.gitpushhackathon.data.action.OAuthAccessTokenStore
import com.github.satoshun.example.gitpushhackathon.oauth.ui.OAuthActivity
import com.github.satoshun.example.gitpushhackathon.ui.feed.FeedActivity
import com.github.satoshun.io.reactivex.lifecycleowner.subscribeOf
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

  @Inject lateinit var oauthStore: OAuthAccessTokenStore
  @Inject lateinit var navigator: LoginNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_act)
    findViewById<View>(R.id.login).setOnClickListener {
      navigator.openOauthLogin()
    }

    watchStore()
  }

  private fun watchStore() {
    oauthStore.onOAuthAccessToken
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOf(this, onNext = {
          navigator.openFeed()
        })
  }
}

@PerActivity
class LoginNavigator @Inject constructor(
    private val activity: AppCompatActivity
) {
  fun openOauthLogin() {
    val intent = Intent(activity, OAuthActivity::class.java)
    activity.startActivity(intent)
  }

  fun openFeed() {
    val intent = Intent(activity, FeedActivity::class.java)
    activity.startActivity(intent)
  }
}
