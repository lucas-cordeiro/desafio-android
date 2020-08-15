package com.picpay.desafio.android.model.base

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.model.data.User
import kotlinx.coroutines.flow.Flow

interface RepositoryBase<T> {
    fun doGetAll(): Flow<List<T>>
    suspend fun doInsertAll(users: List<User>)
    fun clear()
}