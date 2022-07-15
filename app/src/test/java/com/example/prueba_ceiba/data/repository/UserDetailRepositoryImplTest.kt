package com.example.prueba_ceiba.data.repository

import com.example.prueba_ceiba.data.Abstract.ApiUserDetail
import com.example.prueba_ceiba.data.mapper.PostEntityMapper
import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import com.example.prueba_ceiba.domain.entity.Post
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.json.JSONArray
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserDetailRepositoryImplTest {

    @Mock
    lateinit var apiUserDetail: ApiUserDetail

    @Mock
    lateinit var userDetailRepository: UserDetailRepositoryImpl

    @Mock
    lateinit var userDetailInteractor: UserDetailInteractor

    @Mock
    lateinit var postEntityMapper: PostEntityMapper

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userDetailRepository = UserDetailRepositoryImpl(apiUserDetail, postEntityMapper)
        userDetailRepository.setInteractor(userDetailInteractor)
    }

    @Test
    fun getPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(apiUserDetail).getPosts(1)
        userDetailRepository.getPosts(1)
        assert(expect)
    }

    @Test
    fun setListPosts(){
        var expect = false
        val listJsonArray = JSONArray()
        val list = mutableListOf<Post>()
        doAnswer { expect = true }.whenever(userDetailInteractor).setListPost(list)
        userDetailRepository.setListPost(listJsonArray)
        assert(expect)
    }

    @Test
    fun failGetPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(userDetailInteractor).failGetPosts("message")
        userDetailRepository.failGetPosts("message")
        assert(expect)
    }

    @Test
    fun notConnectionServer() {
        var expect = false
        doAnswer { expect = true }. whenever(userDetailInteractor).notConnectionServer()
        userDetailRepository.notConnectionServer()
        assert(expect)
    }

}