package com.github.satoshun.example.gitpushhackathon.feed

import android.os.Bundle
import com.github.satoshun.example.gitpushhackathon.R
import dagger.android.support.DaggerAppCompatActivity

class FeedActivity : DaggerAppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.feed_act)
  }
}
