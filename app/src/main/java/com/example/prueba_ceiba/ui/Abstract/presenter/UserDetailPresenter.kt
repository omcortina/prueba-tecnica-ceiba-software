package com.example.prueba_ceiba.ui.Abstract.presenter

import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.view.UserDetailView

interface UserDetailPresenter {
    fun setView(userDetailView: UserDetailView): Boolean
    fun getPosts(id: Int)
    fun setListPost(list: MutableList<Post>)
    fun failGetPosts(message: String)
    fun notConnectionServer()
}