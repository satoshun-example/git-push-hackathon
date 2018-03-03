package com.github.satoshun.example.gitpushhackathon.ui.oauth

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.github.satoshun.example.gitpushhackathon.BuildConfig
import com.github.satoshun.example.gitpushhackathon.R
import com.github.satoshun.example.gitpushhackathon.databinding.OauthActBinding
import com.github.satoshun.io.reactivex.lifecycleowner.subscribeOf
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class OAuthActivity : DaggerAppCompatActivity() {
  private lateinit var binding: OauthActBinding

  private val url = "https://github.com/login/oauth/authorize?" +
      "scope=user:follow%20repo" +
      "&client_secret=${BuildConfig.CLIENT_SECRET}" +
      "&client_id=${BuildConfig.CLIENT_ID}&" +
      "&redirect_uri=${BuildConfig.SCHEME + "://" + BuildConfig.HOST}"

  @Inject lateinit var store: OAuthActionStore
  @Inject lateinit var client: OAuthWebViewClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.oauth_act)

    with(binding.webview) {
      @SuppressLint("SetJavaScriptEnabled")
      settings.javaScriptEnabled = true
      webChromeClient = WebChromeClient()
      webViewClient = client
    }

    watchStore()

    binding.webview.loadUrl(url)
  }

  private fun watchStore() {
    store.onFinishPage
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOf(this, onNext = {})
  }
}

class OAuthWebViewClient @Inject constructor(
    private val creator: OAuthActionCreator
) : WebViewClient() {
  override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
    super.onPageStarted(view, url, favicon)
    if (!url.isRedirectUrl) return
    val oauthCode = url.oauthCode
    if (oauthCode != null && oauthCode.isNotEmpty()) {
      creator.getAccessToken(oauthCode)
      view.stopLoading()
    }
  }

  private val String.oauthCode get() = Uri.parse(this).getQueryParameter("code")
  private val String.isRedirectUrl get() = startsWith(BuildConfig.SCHEME + "://" + BuildConfig.HOST)
}
