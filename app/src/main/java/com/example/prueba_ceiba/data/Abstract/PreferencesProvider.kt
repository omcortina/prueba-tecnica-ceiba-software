package com.example.prueba_ceiba.data.Abstract

import android.content.Context

interface PreferencesProvider {
    fun writeString(context: Context, key: String, value: String): Boolean
    fun removeString(context: Context, key: String): Boolean
    fun readString(context: Context, key: String): String?
    fun removeAllKeys(context: Context): Boolean
    fun writeBoolean(context: Context, key: String, value: Boolean): Boolean
    fun readBoolean(context: Context, key: String): Boolean?
}