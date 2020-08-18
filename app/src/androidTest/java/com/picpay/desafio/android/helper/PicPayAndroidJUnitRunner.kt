package com.picpay.desafio.android.helper

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener
import com.picpay.desafio.android.PicPayApp


class PicPayAndroidJUnitRunner : AndroidJUnitRunner(){
    @Throws(
        ClassNotFoundException::class,
        IllegalAccessException::class,
        InstantiationException::class
    )
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application? {
        DexOpener.install(this) // Call me first!
        return super.newApplication(cl, PicPayApp::class.java.name, context)
    }
}