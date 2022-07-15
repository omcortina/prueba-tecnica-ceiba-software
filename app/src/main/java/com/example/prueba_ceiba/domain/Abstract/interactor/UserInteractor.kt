package com.example.prueba_ceiba.domain.Abstract.interactor

import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.presenter.UserPresenter

interface UserInteractor {
    fun setPresenter(userPresenter: UserPresenter)
    fun getUsers()
    fun setListUsers(list: MutableList<User>)
    fun failGetUsers(message: String)
    fun notConnectionServer()
}