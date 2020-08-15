package com.picpay.desafio.android

import android.app.Application
import android.view.View
import android.widget.Toast
import com.picpay.desafio.android.di.component.ApplicationComponent
import com.picpay.desafio.android.di.component.DaggerApplicationComponent
import com.picpay.desafio.android.di.module.ApplicationModule
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.model.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PicPayApp : Application(){
    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }
}