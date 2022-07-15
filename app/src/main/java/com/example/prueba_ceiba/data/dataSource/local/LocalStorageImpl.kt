package com.example.prueba_ceiba.data.dataSource.local

import android.content.Context
import com.example.prueba_ceiba.data.Abstract.LocalStorage
import javax.inject.Inject

class LocalStorageImpl@Inject constructor(
    private val preferencesProvider: PreferencesProviderImpl
): LocalStorage {
    private val users = "users"
    override fun saveUsers(context: Context, json: String): Boolean {
        return preferencesProvider.writeString(context, users, json)
    }

    override fun getUsers(context: Context): String? {
        return preferencesProvider.readString(context, users)
    }
}