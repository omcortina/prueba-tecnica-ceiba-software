package com.example.prueba_ceiba.data.dataSource.local

import android.content.Context
import android.preference.PreferenceManager
import com.example.prueba_ceiba.data.Abstract.PreferencesProvider

class PreferencesProviderImpl: PreferencesProvider {
    private val preferenceFileName = "ceiba_preferences"

    override fun writeString(context: Context, key: String, value: String): Boolean {
        val preferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.putString("key", value)
        return editor.commit()
    }

    override fun removeString(context: Context, key: String): Boolean {
        val preferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(key)
        return editor.commit()
    }

    override fun readString(context: Context, key: String): String? {
        val preferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        return preferences.getString(key, preferences.getString(key,null))
    }

    override fun removeAllKeys(context: Context): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.clear()
        return editor.commit()
    }

    override fun writeBoolean(context: Context, key: String, value: Boolean): Boolean {
        val preferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.putBoolean("key", value)
        return editor.commit()
    }

    override fun readBoolean(context: Context, key: String): Boolean? {
        val preferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
        return preferences.getBoolean(key, preferences.getBoolean(key, false))
    }
}