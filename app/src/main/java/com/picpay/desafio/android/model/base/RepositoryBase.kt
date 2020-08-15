package com.picpay.desafio.android.model.base

interface RepositoryBase<T> {
    suspend fun doInsert(t: T): Long
    suspend fun doGetById(id: Int): T?
    suspend fun doGetAll(): List<T>?
    suspend fun doUpdate(t: T): Int
    suspend fun doInsertOrUpdate(t: T)
    suspend fun doBulkInsert(tList: List<T>)
    suspend fun doBulkInsertOrUpdate(tList: List<T>)
    fun clear()
}