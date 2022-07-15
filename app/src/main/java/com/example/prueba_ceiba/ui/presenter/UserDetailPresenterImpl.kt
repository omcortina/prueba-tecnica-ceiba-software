package com.example.prueba_ceiba.ui.presenter

import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.presenter.UserDetailPresenter
import com.example.prueba_ceiba.ui.Abstract.view.UserDetailView

class UserDetailPresenterImpl(
    private val userDetailInteractor: UserDetailInteractor
): UserDetailPresenter {
    init {
        userDetailInteractor.setPresenter(this)
    }

    private var view: UserDetailView? = null

    override fun setView(userDetailView: UserDetailView): Boolean {
        this.view = userDetailView
        return true
    }

    override fun getPosts(id: Int) {
        userDetailInteractor.getPosts(id)
    }

    override fun setListPost(list: MutableList<Post>) {
        view?.setListPost(list)
    }

    override fun failGetPosts(message: String) {
        view?.failGetListPost(message)
    }

    override fun notConnectionServer() {
        view?.notConnectionServer()
    }
}