package com.example.prueba_ceiba.data.Abstract

import android.content.Context

interface LocalStorage {
    fun saveUsers(context: Context, json: String): Boolean
    fun getUsers(context: Context): String?
}