package com.example.prueba_ceiba.ui.Abstract.view

import com.example.prueba_ceiba.domain.entity.Post

interface UserDetailView {
    fun getPosts(id: Int)
    fun setListPost(list: MutableList<Post>)
    fun failGetListPost(message: String)
    fun notConnectionServer()
}