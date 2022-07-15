package com.example.prueba_ceiba.ui.presenter

import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.view.UserView
import com.example.prueba_ceiba.utils.Util
import com.nhaarman.mockitokotlin2.any
import org.mockito.Mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals

class UserPresenterImplTest {

    @Mock
    lateinit var userView: UserView

    @Mock
    lateinit var userPresenter: UserPresenterImpl

    @Mock
    lateinit var userInteractor: UserInteractor

    @Mock
    lateinit var util: Util

    var user: User? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userPresenter = UserPresenterImpl(userInteractor, util)
        userPresenter.setView(userView)
        user = User(1, "Leanne Graham", "3021234567", "leanne@gmail.com")
    }

    @Test
    fun setView(){
        userPresenter.setView(userView)
        assert(true)
    }

    @Test
    fun getUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(userInteractor).getUsers()
        userPresenter.getUsers()
        assert(expect)
    }

    @Test
    fun setListUsers(){
        val expect = mutableListOf<User>()
        doAnswer { expect.add(user!!) }.whenever(userView).setListUsers(any())
        util.saveUsers(expect)
        userPresenter.setListUsers(expect)
        assertEquals(1, expect.size)
    }

    @Test
    fun failGetUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(userView).failGetUsers("message")
        userPresenter.failGetUsers("message")
        assert(expect)
    }

    @Test
    fun notConnectionServer() {
        var expect = false
        doAnswer { expect = true }. whenever(userView).notConnectionServer()
        userPresenter.notConnectionServer()
        assert(expect)
    }
}