package com.picpay.desafio.android.model.db

import com.picpay.desafio.android.model.repository.PicPayDatabase
import com.picpay.desafio.android.model.repository.user.UserRepository
import javax.inject.Inject


class DbControllerImpl @Inject
    constructor(
    override val userRepository: UserRepository,
    private val picPayDatabase: PicPayDatabase
): DbController {
    override fun clearDatabase() {
        picPayDatabase.clearAllTables()
        userRepository.clear()
    }
}