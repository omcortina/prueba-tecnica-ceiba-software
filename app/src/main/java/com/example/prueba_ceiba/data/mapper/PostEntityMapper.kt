package com.example.prueba_ceiba.data.mapper

import com.example.prueba_ceiba.domain.entity.Post
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class PostEntityMapper {
    fun transform(jsonArray: JSONArray?): MutableList<Post>{
        val listPost: MutableList<Post> = mutableListOf()
        if(jsonArray != null){
            for (i in 0 until jsonArray.length()){
                transformPost(jsonArray.getJSONObject(i))?.let{ listPost.add(it) }
            }
        }

        return listPost
    }

    fun transformPost(json: JSONObject?): Post?{
        if(json != null){
            return try {
                Post(
                    json.getInt("id"),
                    json.getInt("userId"),
                    json.getString("title"),
                    json.getString("body")
                )
            }catch (e: JSONException){
                null
            }
        }
        return null
    }
}