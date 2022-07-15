package com.example.prueba_ceiba.ui.presenter

import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.presenter.UserPresenter
import com.example.prueba_ceiba.ui.Abstract.view.UserView
import com.example.prueba_ceiba.utils.Util

class UserPresenterImpl(
    private val userInteractor: UserInteractor,
    private val util: Util
): UserPresenter {

    init {
        userInteractor.setPresenter(this)
    }

    private var view: UserView? = null

    override fun setView(userView: UserView): Boolean {
        this.view = userView
        return true
    }

    override fun getUsers() {
        userInteractor.getUsers()
    }

    override fun setListUsers(list: MutableList<User>) {
        view?.setListUsers(list)
        Thread{
            util.saveUsers(list)
        }.start()
    }

    override fun failGetUsers(message: String) {
        view?.failGetUsers(message)
    }

    override fun notConnectionServer() {
        view?.notConnectionServer()
    }
}