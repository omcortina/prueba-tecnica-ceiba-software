package com.example.prueba_ceiba.domain.Abstract.repository

import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import org.json.JSONArray

interface UserDetailRepository {
    fun setInteractor(userDetailInteractor: UserDetailInteractor)
    fun getPosts(id: Int)
    fun setListPost(listJson: JSONArray)
    fun failGetPosts(message: String)
    fun notConnectionServer()
}