package com.picpay.desafio.android.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.db.dao.UserDao

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class PicPayDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
}