package com.picpay.desafio.android.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.db.DbContract
import com.picpay.desafio.android.model.db.DbContract.UserContract.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun doGetAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun doInsertAll(users: List<User>)

    @Query("DELETE FROM $TABLE_NAME")
    fun clear()
}