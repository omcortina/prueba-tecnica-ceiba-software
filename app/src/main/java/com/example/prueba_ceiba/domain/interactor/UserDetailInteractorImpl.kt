package com.example.prueba_ceiba.domain.interactor

import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import com.example.prueba_ceiba.domain.Abstract.repository.UserDetailRepository
import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.presenter.UserDetailPresenter

class UserDetailInteractorImpl(
    private val userDetailRepository: UserDetailRepository
): UserDetailInteractor {

    init {
        userDetailRepository.setInteractor(this)
    }

    private lateinit var presenter: UserDetailPresenter

    override fun setPresenter(userDetailPresenter: UserDetailPresenter) {
        presenter = userDetailPresenter
    }

    override fun getPosts(id: Int) {
        userDetailRepository.getPosts(id)
    }

    override fun setListPost(list: MutableList<Post>) {
        presenter.setListPost(list)
    }

    override fun failGetPosts(message: String) {
        presenter.failGetPosts(message)
    }

    override fun notConnectionServer() {
        presenter.notConnectionServer()
    }
}