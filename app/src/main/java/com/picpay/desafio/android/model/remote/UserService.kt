package com.picpay.desafio.android.model.remote

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.base.ServiceBase
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    companion object Factory{
        fun create(): UserService {
            val retrofit = ServiceBase()
                .create(BuildConfig.BASE_URL)
            return retrofit.create(UserService::class.java)
        }
    }
}