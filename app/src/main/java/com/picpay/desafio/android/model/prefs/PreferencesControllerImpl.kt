package com.picpay.desafio.android.model.prefs

import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesControllerImpl @Inject
constructor(
    val context: Context
) : PreferencesController {

    init {
        Prefs.Builder()
            .setContext(context)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(context.packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    override var currentCacheTime: Long
        get() = Prefs.getLong(PREF_KEY_CACHE_TIME, 0)
        set(value) {
            Prefs.putLong(PREF_KEY_CACHE_TIME, value)
        }

    companion object {
        private val PREF_KEY_CACHE_TIME = "PREF_KEY_CACHE_TIME"
    }
}