package com.example.prueba_ceiba.utils

import android.app.Activity
import android.content.Context
import com.example.prueba_ceiba.R
import com.example.prueba_ceiba.data.dataSource.local.LocalStorageImpl
import com.example.prueba_ceiba.data.dataSource.local.PreferencesProviderImpl
import com.example.prueba_ceiba.domain.entity.User
import com.google.gson.Gson
import com.tapadoo.alerter.Alerter
import org.json.JSONArray

class Util constructor(private val localStorageImpl: LocalStorageImpl) {
    lateinit var applicationContext: Context

    fun setContext(context: Context) {
        applicationContext = context
    }

    fun toast(text: String, activity: Activity, type: String? = null) {
        var color = R.color.colorPrimary
        when (type) {
            "success" -> {
                color = R.color.colorMediumSeaGreen
            }
            "error" -> {
                color = R.color.colorValentineRed
            }
            "info" -> {
                color = R.color.colorPrimary
            }
        }
        Alerter.create(activity)
            .setText(text)
            .setBackgroundColorRes(color) // or setBackgroundColorInt(Color.CYAN)
            .enableSwipeToDismiss()
            .show()
    }

    fun saveUsers(users: MutableList<User>): Boolean{
        return localStorageImpl.saveUsers(applicationContext, Gson().toJson(users))
    }

    fun getUsers(): MutableList<User>{
        val users: MutableList<User> = mutableListOf()
        val gson = Gson()
        localStorageImpl.getUsers(applicationContext)?.let {
            val json = JSONArray(it)
            for (i in 0 until json.length()){
                users.add(
                    gson.fromJson(
                        json.getJSONObject(i).toString(),
                        User::class.java
                    )
                )
            }
        }
        return users
    }

    companion object {
        val instance = Util(
            LocalStorageImpl(PreferencesProviderImpl())
        )
    }
}