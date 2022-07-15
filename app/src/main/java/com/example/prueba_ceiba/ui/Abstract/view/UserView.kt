package com.example.prueba_ceiba.ui.Abstract.view

import com.example.prueba_ceiba.domain.entity.User

interface UserView {
    fun getUsers()
    fun setListUsers(list: MutableList<User>)
    fun failGetUsers(message: String)
    fun notConnectionServer()
    fun goToPosts(id: Int, name: String, phone: String, email: String)
}