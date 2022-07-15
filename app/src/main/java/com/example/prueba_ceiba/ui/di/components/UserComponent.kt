package com.example.prueba_ceiba.ui.di.components

import com.example.prueba_ceiba.ui.di.modules.UserModule
import com.example.prueba_ceiba.ui.view.UserActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [UserModule::class])
interface UserComponent {
    fun inject(userActivity: UserActivity)
}