package com.example.prueba_ceiba.domain.Abstract.interactor

import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.presenter.UserDetailPresenter

interface UserDetailInteractor {
    fun setPresenter(userDetailPresenter: UserDetailPresenter)
    fun getPosts(id: Int)
    fun setListPost(list: MutableList<Post>)
    fun failGetPosts(message: String)
    fun notConnectionServer()
}