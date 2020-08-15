package com.picpay.desafio.android.di.component

import com.picpay.desafio.android.di.annotations.PerActivity
import com.picpay.desafio.android.di.module.ActivityModule
import com.picpay.desafio.android.view.main.MainActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}