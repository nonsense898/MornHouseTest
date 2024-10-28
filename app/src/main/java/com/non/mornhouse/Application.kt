package com.non.mornhouse

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Application : Application() {
    override fun onTerminate() {
        super.onTerminate()
        Log.w("TAG", "onTerminate: ")
    }
}