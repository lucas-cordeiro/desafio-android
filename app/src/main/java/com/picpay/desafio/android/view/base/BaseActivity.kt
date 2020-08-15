package com.picpay.desafio.android.view.base

import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.PicPayApp
import com.picpay.desafio.android.di.component.ActivityComponent
import com.picpay.desafio.android.di.component.DaggerActivityComponent
import com.picpay.desafio.android.di.module.ActivityModule

abstract class BaseActivity(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .applicationComponent((application as PicPayApp).applicationComponent)
            .build()
    }
}