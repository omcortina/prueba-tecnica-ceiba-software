package com.example.prueba_ceiba.ui.presenter

import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.view.UserDetailView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserDetailPresenterImplTest {

    @Mock
    lateinit var userDetailView: UserDetailView

    @Mock
    lateinit var userDetailPresenter: UserDetailPresenterImpl

    @Mock
    lateinit var userDetailInteractor: UserDetailInteractor

    var post: Post? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userDetailPresenter = UserDetailPresenterImpl(userDetailInteractor)
        userDetailPresenter.setView(userDetailView)
        post = Post(1, 1, "title", "body")
    }

    @Test
    fun setView(){
        userDetailPresenter.setView(userDetailView)
        assert(true)
    }

    @Test
    fun getPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(userDetailInteractor).getPosts(1)
        userDetailPresenter.getPosts(1)
        assert(expect)
    }

    @Test
    fun setListPosts(){
        val expect = mutableListOf<Post>()
        doAnswer { expect.add(post!!) }.whenever(userDetailView).setListPost(any())
        userDetailPresenter.setListPost(expect)
        assertEquals(1, expect.size)
    }

    @Test
    fun failGetPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(userDetailView).failGetListPost("message")
        userDetailPresenter.failGetPosts("message")
        assert(expect)
    }

    @Test
    fun notConnectionServer() {
        var expect = false
        doAnswer { expect = true }. whenever(userDetailView).notConnectionServer()
        userDetailPresenter.notConnectionServer()
        assert(expect)
    }
}