package com.picpay.desafio.android.model.db

import com.picpay.desafio.android.model.repository.user.UserRepository

interface DbController {
    val userRepository: UserRepository
    fun clearDatabase()
}