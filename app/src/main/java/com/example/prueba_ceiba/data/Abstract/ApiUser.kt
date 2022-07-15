package com.example.prueba_ceiba.data.Abstract

import com.example.prueba_ceiba.domain.Abstract.repository.UserRepository

interface ApiUser {
    fun setRepository(userRepository: UserRepository)
    fun getUsers()
}