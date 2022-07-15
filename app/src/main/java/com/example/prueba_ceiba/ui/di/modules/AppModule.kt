package com.example.prueba_ceiba.ui.di.modules

import android.content.Context
import com.example.prueba_ceiba.ui.App
import com.loopj.android.http.AsyncHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val  application : App) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideClient() = AsyncHttpClient()
}