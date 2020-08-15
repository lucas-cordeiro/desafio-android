package com.picpay.desafio.android.model

import com.picpay.desafio.android.model.db.DbController
import com.picpay.desafio.android.model.remote.UserService
import com.picpay.desafio.android.model.repository.user.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val dbController: DbController
): DataManager {

    //DAO
    override val userRepository = dbController.userRepository

    //API
    override val userService = UserService.create()

    override fun clearDatabase() {
        dbController.clearDatabase()
    }
}