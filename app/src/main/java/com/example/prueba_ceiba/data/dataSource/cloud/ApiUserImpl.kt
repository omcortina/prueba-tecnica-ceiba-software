package com.example.prueba_ceiba.data.dataSource.cloud

import com.example.prueba_ceiba.data.Abstract.ApiUser
import com.example.prueba_ceiba.data.EndPoints
import com.example.prueba_ceiba.domain.Abstract.repository.UserRepository
import com.example.prueba_ceiba.utils.Util
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.MySSLSocketFactory
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException

class ApiUserImpl(
    private val client: AsyncHttpClient,
    private val endPoints: EndPoints
): ApiUser {

    init {
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory())
    }

    private lateinit var repository: UserRepository

    override fun setRepository(userRepository: UserRepository) {
        repository = userRepository
    }

    override fun getUsers() {
        client.get(endPoints.users, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                when(statusCode){
                    Http.OK.status -> {
                        try {
                            val requestBody = responseBody?.let { String(it) }
                            requestBody?.let {
                                val response = JSONArray(it)
                                repository.setListUsers(response)
                            }
                        }catch (exception: JSONException){
                            repository.failGetUsers("Ocurrió un error en el servidor")
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
                        repository.failGetUsers("No se pudo obtener los datos de los usuarios")
                    }

                    Http.NOT_CONNECTION.status -> {
                        repository.notConnectionServer()
                    }

                    else -> {
                        repository.failGetUsers("Ocurrió un error en el servidor")
                    }
                }
            }
        })
    }
}