package com.picpay.desafio.android.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.model.base.ModelBase
import com.picpay.desafio.android.model.db.DbContract
import kotlinx.android.parcel.Parcelize
@Entity(
    tableName = DbContract.UserContract.TABLE_NAME,
    indices = [Index(value = [DbContract.UserContract.Collumns.ID], unique = true)]
)
@Parcelize
data class User(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
) : Parcelable, ModelBase {
    override fun diff() = name
}