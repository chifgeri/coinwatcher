package com.xd4bhs.coinwatcher

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CoinWatcherApplication: Application() {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        mFirebaseAnalytics = Firebase.analytics

    }
}