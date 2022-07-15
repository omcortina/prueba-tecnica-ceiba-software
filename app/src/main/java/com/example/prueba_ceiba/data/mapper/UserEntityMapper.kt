package com.example.prueba_ceiba.data.mapper

import com.example.prueba_ceiba.domain.entity.User
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class UserEntityMapper {
    fun transform(jsonArray: JSONArray?): MutableList<User>{
        val listUser: MutableList<User> = mutableListOf()
        if(jsonArray != null){
            for (i in 0 until jsonArray.length()){
                transformUser(jsonArray.getJSONObject(i))?.let { listUser.add(it) }
            }
        }

        return listUser
    }

    fun transformUser(json: JSONObject?): User?{
        if(json != null){
            return try {
                User(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getString("phone"),
                    json.getString("email")
                )
            }catch (e: JSONException){
                null
            }
        }
        return null
    }
}