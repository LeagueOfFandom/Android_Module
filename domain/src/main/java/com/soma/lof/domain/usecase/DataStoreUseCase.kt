package com.soma.lof.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.soma.lof.domain.util.FCM_TOKEN_KEY
import com.soma.lof.domain.util.JWT_TOKEN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>,
) {

    val jwtToken: Flow<String?> =
        dataStore.data.map { preferences -> preferences[stringPreferencesKey(JWT_TOKEN_KEY)] }

    val fcmToken: Flow<String?> =
        dataStore.data.map { preferences -> preferences[stringPreferencesKey(FCM_TOKEN_KEY)] }

    suspend fun editJwtToken(jwtToken: String) {
        dataStore.edit {
            it[stringPreferencesKey(JWT_TOKEN_KEY)] = jwtToken
        }
    }

    suspend fun editFcmToken(fcmToken: String) {
        dataStore.edit {
            it[stringPreferencesKey(FCM_TOKEN_KEY)] = fcmToken
        }
    }

    suspend fun removeJwtToken() {
        dataStore.edit {
            it.remove(stringPreferencesKey(JWT_TOKEN_KEY))
        }
    }

    suspend fun removeFcmToken() {
        dataStore.edit {
            it.remove(stringPreferencesKey(FCM_TOKEN_KEY))
        }
    }
}