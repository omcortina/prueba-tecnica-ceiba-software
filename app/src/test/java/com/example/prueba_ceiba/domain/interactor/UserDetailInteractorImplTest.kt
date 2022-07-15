package com.example.prueba_ceiba.domain.interactor

import com.example.prueba_ceiba.domain.Abstract.repository.UserDetailRepository
import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.presenter.UserDetailPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserDetailInteractorImplTest {

    @Mock
    lateinit var userDetailRepository: UserDetailRepository

    @Mock
    lateinit var userDetaiInteractor: UserDetailInteractorImpl

    @Mock
    lateinit var userDetailPresenter: UserDetailPresenter

    var post: Post? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userDetaiInteractor = UserDetailInteractorImpl(userDetailRepository)
        userDetaiInteractor.setPresenter(userDetailPresenter)
        post = Post(1, 1, "title", "body")
    }

    @Test
    fun getPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(userDetailRepository).getPosts(1)
        userDetaiInteractor.getPosts(1)
        assert(expect)
    }

    @Test
    fun setListPosts(){
        val expect = mutableListOf<Post>()
        doAnswer { expect.add(post!!) }.whenever(userDetailPresenter).setListPost(any())
        userDetaiInteractor.setListPost(expect)
        Assert.assertEquals(1, expect.size)
    }

    @Test
    fun failGetPosts(){
        var expect = false
        doAnswer { expect = true }.whenever(userDetailPresenter).failGetPosts("message")
        userDetaiInteractor.failGetPosts("message")
        assert(expect)
    }

    @Test
    fun notConnectionServer() {
        var expect = false
        doAnswer { expect = true }. whenever(userDetailPresenter).notConnectionServer()
        userDetaiInteractor.notConnectionServer()
        assert(expect)
    }
}