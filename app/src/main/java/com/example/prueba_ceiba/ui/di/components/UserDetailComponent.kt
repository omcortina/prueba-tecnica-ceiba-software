package com.example.prueba_ceiba.ui.di.components

import com.example.prueba_ceiba.ui.di.modules.UserDetailModule
import com.example.prueba_ceiba.ui.view.UserDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserDetailModule::class])
interface UserDetailComponent {
    fun inject(userDetailActivity: UserDetailActivity)
}