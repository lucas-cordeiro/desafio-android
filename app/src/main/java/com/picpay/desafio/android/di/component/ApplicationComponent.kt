package com.picpay.desafio.android.di.component

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.picpay.desafio.android.PicPayApp
import com.picpay.desafio.android.di.annotations.ApplicationContext
import com.picpay.desafio.android.di.module.ApplicationModule
import com.picpay.desafio.android.model.DataManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    val dataManager: DataManager
    fun inject(app: PicPayApp)
    fun application(): Application
    @ApplicationContext
    fun context(): Context
}
