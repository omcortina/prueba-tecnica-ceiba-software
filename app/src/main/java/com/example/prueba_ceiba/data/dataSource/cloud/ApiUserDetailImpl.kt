package com.example.prueba_ceiba.data.dataSource.cloud

import com.example.prueba_ceiba.data.Abstract.ApiUserDetail
import com.example.prueba_ceiba.data.EndPoints
import com.example.prueba_ceiba.domain.Abstract.repository.UserDetailRepository
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.MySSLSocketFactory
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException

class ApiUserDetailImpl(
    private val client: AsyncHttpClient,
    private val endPoints: EndPoints
): ApiUserDetail {

    init {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory())
    }

    private lateinit var repository: UserDetailRepository

    override fun setRepository(userDetailRepository: UserDetailRepository) {
        repository = userDetailRepository
    }

    override fun getPosts(id: Int) {
        client.get(endPoints.posts + id, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                when(statusCode){
                    Http.OK.status -> {
                        try {
                            val requestBody = responseBody?.let { String(it) }
                            requestBody?.let{
                                val response = JSONArray(it)
                                repository.setListPost(response)
                            }
                        }catch (exception: JSONException){
                            repository.failGetPosts("Ocurrió un error en el servidor")
                        }
                    }
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                when(statusCode){

                    Http.BAD_REQUEST.status -> {
                        repository.failGetPosts("No se pudo obtener los datos de los usuarios")
                    }

                    Http.NOT_CONNECTION.status -> {
                        repository.notConnectionServer()
                    }

                    else -> {
                        repository.failGetPosts("Ocurrió un error en el servidor")
                    }
                }
            }

        })
    }
}