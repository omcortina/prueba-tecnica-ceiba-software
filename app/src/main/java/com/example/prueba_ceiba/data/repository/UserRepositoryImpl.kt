package com.example.prueba_ceiba.data.repository

import com.example.prueba_ceiba.data.Abstract.ApiUser
import com.example.prueba_ceiba.data.mapper.UserEntityMapper
import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.Abstract.repository.UserRepository
import org.json.JSONArray

class UserRepositoryImpl(
    private val apiUser: ApiUser,
    private val userEntityMapper: UserEntityMapper
): UserRepository {

    init {
        apiUser.setRepository(this)
    }

    private lateinit var interactor: UserInteractor

    override fun setInteractor(userInteractor: UserInteractor) {
        interactor = userInteractor
    }

    override fun getUsers() {
        apiUser.getUsers()
    }

    override fun setListUsers(listJson: JSONArray) {
        val list = userEntityMapper.transform(listJson)
        interactor.setListUsers(list)
    }

    override fun failGetUsers(message: String) {
        interactor.failGetUsers(message)
    }

    override fun notConnectionServer() {
        interactor.notConnectionServer()
    }
}