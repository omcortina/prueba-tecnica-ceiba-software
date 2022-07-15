package com.example.prueba_ceiba.domain.Abstract.repository

import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import org.json.JSONArray

interface UserRepository {
    fun setInteractor(userInteractor: UserInteractor)
    fun getUsers()
    fun setListUsers(listJson: JSONArray)
    fun failGetUsers(message: String)
    fun notConnectionServer()
}