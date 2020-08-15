package com.picpay.desafio.android.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.di.annotations.ApplicationContext
import com.picpay.desafio.android.model.AppDataManager
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.model.db.DbController
import com.picpay.desafio.android.model.db.DbControllerImpl
import com.picpay.desafio.android.model.repository.PicPayDatabase
import com.picpay.desafio.android.model.repository.user.UserRepository
import com.picpay.desafio.android.model.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {
    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideDbController(dbController: DbControllerImpl): DbController {
        return dbController
    }

    @Provides
    @Singleton
    internal fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository {
        return userRepository
    }

    @Provides
    @Singleton
    internal fun providePicPayDatabase(): PicPayDatabase {
        return Room.databaseBuilder(
            mApplication,
            PicPayDatabase::class.java,
            "com.picpay.desafio.android.db"
        )
            .fallbackToDestructiveMigration().build()
    }
}