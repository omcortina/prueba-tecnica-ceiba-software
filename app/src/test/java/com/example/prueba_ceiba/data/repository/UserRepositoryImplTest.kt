package com.example.prueba_ceiba.data.repository

import com.example.prueba_ceiba.data.Abstract.ApiUser
import com.example.prueba_ceiba.data.mapper.UserEntityMapper
import com.example.prueba_ceiba.domain.Abstract.interactor.UserInteractor
import com.example.prueba_ceiba.domain.entity.User
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.json.JSONArray
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserRepositoryImplTest {

    @Mock
    lateinit var apiUser: ApiUser

    @Mock
    lateinit var userRepository: UserRepositoryImpl

    @Mock
    lateinit var userInteractor: UserInteractor

    @Mock
    lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepositoryImpl(apiUser, userEntityMapper)
        userRepository.setInteractor(userInteractor)
    }

    @Test
    fun getUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(apiUser).getUsers()
        userRepository.getUsers()
        assert(expect)
    }

    @Test
    fun setListUsers(){
        var expect = false
        val listJsonArray = JSONArray()
        val list = mutableListOf<User>()
        doAnswer { expect = true }.whenever(userInteractor).setListUsers(list)
        userRepository.setListUsers(listJsonArray)
        assert(expect)
    }

    @Test
    fun failGetUsers(){
        var expect = false
        doAnswer { expect = true }.whenever(userInteractor).failGetUsers("message")
        userRepository.failGetUsers("message")
        assert(expect)
    }

    @Test
    fun noConnectionServer(){
        var expect = false
        doAnswer { expect = true }. whenever(userInteractor).notConnectionServer()
        userRepository.notConnectionServer()
        assert(expect)
    }
}