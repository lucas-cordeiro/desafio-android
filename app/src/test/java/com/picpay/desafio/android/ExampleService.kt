package com.picpay.desafio.android

import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.remote.UserService

class ExampleService(
    private val service: UserService
) {

    suspend fun example(): List<User> {
        val users = service.getUsers()
        return users ?: emptyList()
    }
}