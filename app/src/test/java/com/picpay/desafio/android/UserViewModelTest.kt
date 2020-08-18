package com.picpay.desafio.android

import com.picpay.desafio.android.di.component.ApplicationComponent
import com.picpay.desafio.android.di.module.ApplicationModule
import com.picpay.desafio.android.helper.CACHE_DURATION
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.viewmodel.UserViewModel
import it.cosenonjaviste.daggermock.DaggerMock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, constants = BuildConfig::class, sdk = [24])
class UserViewModelTest {

    private lateinit var dataManager: DataManager

    private lateinit var viewModel: UserViewModel

    @get:Rule var rule = DaggerMock.rule<ApplicationComponent>(ApplicationModule(RuntimeEnvironment.application)){
        set {
            dataManager = it.dataManager
            viewModel = UserViewModel(dataManager)
        }
    }

    @Test
    fun isValidShouldUpdateUsersCache(){
        dataManager.currentCacheTime = System.currentTimeMillis() - (CACHE_DURATION + 200)
        assertTrue(viewModel.shouldUpdateUsersCache())
        assertFalse(viewModel.shouldUpdateUsersCache())
    }

    @Test
    fun isValidFetchRecentUsers() {
        runBlocking {
            assertTrue(dataManager.userRepository.doGetAll().first().isEmpty())
            viewModel.fetchRecentUsers()
            val users = dataManager.userRepository.doGetAll().first()
            assertEquals(users, dataManager.userService.getUsers())
        }
    }
}