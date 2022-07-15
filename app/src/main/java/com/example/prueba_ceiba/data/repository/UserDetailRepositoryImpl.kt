package com.example.prueba_ceiba.data.repository

import com.example.prueba_ceiba.data.Abstract.ApiUserDetail
import com.example.prueba_ceiba.data.mapper.PostEntityMapper
import com.example.prueba_ceiba.domain.Abstract.interactor.UserDetailInteractor
import com.example.prueba_ceiba.domain.Abstract.repository.UserDetailRepository
import org.json.JSONArray

class UserDetailRepositoryImpl(
    private val apiUserDetail: ApiUserDetail,
    private val postEntityMapper: PostEntityMapper
): UserDetailRepository {

    init {
        apiUserDetail.setRepository(this)
    }

    private lateinit var interactor: UserDetailInteractor

    override fun setInteractor(userDetailInteractor: UserDetailInteractor) {
        interactor = userDetailInteractor
    }

    override fun getPosts(id: Int) {
        apiUserDetail.getPosts(id)
    }

    override fun setListPost(listJson: JSONArray) {
        val list = postEntityMapper.transform(listJson)
        interactor.setListPost(list)
    }

    override fun failGetPosts(message: String) {
        interactor.failGetPosts(message)
    }

    override fun notConnectionServer() {
        interactor.notConnectionServer()
    }
}