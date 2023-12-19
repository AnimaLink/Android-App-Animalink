package com.riveong.animalink.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userProfile")
        private val USERNAME = stringPreferencesKey("username")
        private val EMAIL = stringPreferencesKey("email")
        private val AVATAR = stringPreferencesKey("avatar")
    }

    val getUsernameProfile: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USERNAME] ?: ""
    }
    val getEmailProfile: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[EMAIL] ?: ""
    }
    val getAvatarProfile: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[AVATAR] ?: ""
    }

    suspend fun saveProfile(username: String = "undefined", email: String = "undefined", avatar: String = "undefined") {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = username
            preferences[EMAIL] = email
            preferences[AVATAR] = avatar

        }
    }
}