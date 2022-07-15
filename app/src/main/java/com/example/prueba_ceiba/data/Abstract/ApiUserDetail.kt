package com.example.prueba_ceiba.data.Abstract

import com.example.prueba_ceiba.domain.Abstract.repository.UserDetailRepository

interface ApiUserDetail {
    fun setRepository(userDetailRepository: UserDetailRepository)
    fun getPosts(id: Int)
}