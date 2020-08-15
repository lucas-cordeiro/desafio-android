package com.picpay.desafio.android.model.repository.user

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.repository.PicPayDatabase
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val picPayDatabase: PicPayDatabase): UserRepository{
    private val userDao = picPayDatabase.userDao()

    override fun doGetAll() = userDao.doGetAll()

    override suspend fun doInsertAll(users: List<User>) = userDao.doInsertAll(users)

    override fun clear() {
        userDao.clear()
    }
}