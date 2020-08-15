package com.picpay.desafio.android.model.repository.user

import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.repository.PicPayDatabase
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val picPayDatabase: PicPayDatabase): UserRepository{
    private val userDao = picPayDatabase.userDao()

    override suspend fun doInsert(t: User) = userDao.doInsert(t)

    override suspend fun doGetById(id: Int): User? {
        TODO("Not yet implemented")
    }

    override suspend fun doGetAll(): List<User>? {
        TODO("Not yet implemented")
    }

    override suspend fun doUpdate(t: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun doInsertOrUpdate(t: User) {
        TODO("Not yet implemented")
    }

    override suspend fun doBulkInsert(tList: List<User>) {
        TODO("Not yet implemented")
    }

    override suspend fun doBulkInsertOrUpdate(tList: List<User>) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        userDao.clear()
    }
}