package com.example.prueba_ceiba.ui.di.components

import com.example.prueba_ceiba.ui.App
import com.example.prueba_ceiba.ui.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])

interface AppComponent {
    fun inject(app: App)
}