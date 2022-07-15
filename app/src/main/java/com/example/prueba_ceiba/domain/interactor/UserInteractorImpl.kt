package com.example.prueba_ceiba.domain.interactor

import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.Abstract.repository.UserRepository
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.presenter.UserPresenter

class UserInteractorImpl(
    private val userRepository: UserRepository
): UserInteractor {

    init {
        userRepository.setInteractor(this)
    }

    private lateinit var presenter: UserPresenter

    override fun setPresenter(userPresenter: UserPresenter) {
        presenter = userPresenter
    }

    override fun getUsers() {
        userRepository.getUsers()
    }

    override fun setListUsers(list: MutableList<User>) {
        presenter.setListUsers(list)
    }

    override fun failGetUsers(message: String) {
        presenter.failGetUsers(message)
    }

    override fun notConnectionServer() {
        presenter.notConnectionServer()
    }
}