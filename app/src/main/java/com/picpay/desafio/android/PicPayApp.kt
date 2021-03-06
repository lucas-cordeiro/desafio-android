package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.component.ApplicationComponent
import com.picpay.desafio.android.di.component.DaggerApplicationComponent
import com.picpay.desafio.android.di.module.ApplicationModule
import com.picpay.desafio.android.model.DataManager
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