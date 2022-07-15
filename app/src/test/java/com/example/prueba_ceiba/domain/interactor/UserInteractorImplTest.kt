package com.example.prueba_ceiba.domain.interactor

import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.Abstract.repository.UserRepository
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.presenter.UserPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserInteractorImplTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var userInteractor: UserInteractorImpl

    @Mock
    lateinit var userPresenter: UserPresenter

    var user: User? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userInteractor = UserInteractorImpl(userRepository)
        userInteractor.setPresenter(userPresenter)
        user = User(1, "Leanne Graham", "3021234567", "leanne@gmail.com")
    }

    @Test
    fun getUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(userRepository).getUsers()
        userInteractor.getUsers()
        assert(expect)
    }

    @Test
    fun setListUsers(){
        val expect = mutableListOf<User>()
        doAnswer { expect.add(user!!) }.whenever(userPresenter).setListUsers(any())
        userInteractor.setListUsers(expect)
        Assert.assertEquals(1, expect.size)
    }

    @Test
    fun failGetUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(userPresenter).failGetUsers("message")
        userInteractor.failGetUsers("message")
        assert(expect)
    }

    @Test
    fun noConnectionServer(){
        var expect = false
        doAnswer { expect = true }. whenever(userPresenter).notConnectionServer()
        userInteractor.notConnectionServer()
        assert(expect)
    }
}