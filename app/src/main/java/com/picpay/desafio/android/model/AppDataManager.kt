package com.picpay.desafio.android.model

import com.picpay.desafio.android.model.db.DbController
import com.picpay.desafio.android.model.prefs.PreferencesController
import com.picpay.desafio.android.model.remote.UserService
import com.picpay.desafio.android.model.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val dbController: DbController,
    private val mPreferencesController: PreferencesController
): DataManager {

    //DAO
    override val userRepository = dbController.userRepository

    //API
    override val userService = UserService.create()

    override var currentCacheTime: Long
        get() = mPreferencesController.currentCacheTime
        set(value) {
            mPreferencesController.currentCacheTime = value
        }

    override fun clearDatabase() {
        dbController.clearDatabase()
    }
}