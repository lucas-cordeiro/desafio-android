package com.picpay.desafio.android.model

import com.picpay.desafio.android.model.db.DbController
import com.picpay.desafio.android.model.prefs.PreferencesController
import com.picpay.desafio.android.model.remote.UserService

interface DataManager: DbController, PreferencesController {
    val userService: UserService
}