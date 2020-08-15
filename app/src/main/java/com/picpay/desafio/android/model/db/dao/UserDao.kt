package com.picpay.desafio.android.model.db.dao

import androidx.room.*
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.db.DbContract
import com.picpay.desafio.android.model.db.DbContract.UserContract.TABLE_NAME

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun doInsert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun doInsertOrUpdate(user: User): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun doUpdate(user: User): Int

    @Delete
    suspend fun doDelete(user: User): Int

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun doGetAll(): List<User?>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${DbContract.UserContract.Collumns.ID} = :id")
    suspend fun doGetById(id: Int) : User?

    @Query("DELETE FROM $TABLE_NAME")
    fun clear()
}