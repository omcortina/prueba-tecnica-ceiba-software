package com.example.prueba_ceiba.data.mapper

import junit.framework.Assert
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserEntityMapperTest {

    @Mock
    lateinit var userEntityMapper: UserEntityMapper

    private var userJson = JSONObject()
    private var userJsonArray = JSONArray()
    private var userJsonEmpty: JSONObject? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        userEntityMapper = UserEntityMapper()
    }

    @Test
    fun transformList(){
        userJson.put("id", 1)
        userJson.put("name", "Example")
        userJson.put("phone", "3011234567")
        userJson.put("email", "example@gmail.com")
        userJsonArray.put(userJson)
        val expect = userEntityMapper.transform(userJsonArray)
        assertEquals(1, expect.size)
    }

    @Test
    fun transformNull(){
        val expect = userEntityMapper.transformUser(userJsonEmpty)
        assertEquals(null, expect)
    }

    @Test
    fun transformException(){
        userJson.put("id", "")
        val expect = userEntityMapper.transformUser(userJson)
        assertEquals(null, expect)
    }
}