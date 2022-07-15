package com.example.prueba_ceiba.ui.Abstract.presenter

import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.view.UserView

interface UserPresenter {
    fun setView(userView: UserView): Boolean
    fun getUsers()
    fun setListUsers(list: MutableList<User>)
    fun failGetUsers(message: String)
    fun notConnectionServer()
}