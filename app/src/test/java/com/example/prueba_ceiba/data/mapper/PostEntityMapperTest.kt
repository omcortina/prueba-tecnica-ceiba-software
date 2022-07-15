package com.example.prueba_ceiba.data.mapper

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostEntityMapperTest {

    @Mock
    lateinit var postEntityMapper: PostEntityMapper

    private var postJson = JSONObject()
    private var postJsonArray = JSONArray()
    private var postJsonEmpty: JSONObject? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        postEntityMapper = PostEntityMapper()
    }

    @Test
    fun transformList(){
        postJson.put("id", 1)
        postJson.put("userId", 1)
        postJson.put("title", "title")
        postJson.put("body", "body")
        postJsonArray.put(postJson)
        val expect = postEntityMapper.transform(postJsonArray)
        Assert.assertEquals(1, expect.size)
    }

    @Test
    fun transformNull(){
        val expect = postEntityMapper.transformPost(postJsonEmpty)
        Assert.assertEquals(null, expect)
    }

    @Test
    fun transformException(){
        postJson.put("id", "")
        val expect = postEntityMapper.transformPost(postJson)
        Assert.assertEquals(null, expect)
    }
}