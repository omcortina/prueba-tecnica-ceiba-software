package com.example.prueba_ceiba.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.example.prueba_ceiba.ui.di.components.AppComponent
import com.example.prueba_ceiba.ui.di.components.DaggerAppComponent
import com.example.prueba_ceiba.utils.Util

class App: Application(), Application.ActivityLifecycleCallbacks {
    private lateinit var appComponent: AppComponent
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        Util.instance.setContext(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}