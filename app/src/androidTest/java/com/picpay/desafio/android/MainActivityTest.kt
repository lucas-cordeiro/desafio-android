package com.picpay.desafio.android

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.view.main.MainActivity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var dataManager: DataManager

    @Before
    fun setUp(){
        val app = context.applicationContext as PicPayApp
        dataManager = app.applicationComponent.dataManager
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        runBlocking {
            val users = dataManager.userService.getUsers()

            assertTrue(!users.isNullOrEmpty())

            launchActivity<MainActivity>().apply {
                moveToState(Lifecycle.State.RESUMED)

                assertEquals(successResponse, users?.get(0))
                checkRecyclerViewItem(R.id.recyclerView, 0, withText(successResponse.name))
                checkRecyclerViewItem(R.id.recyclerView, 0, withText(successResponse.username))
            }
        }
    }

    companion object {
        private val successResponse by lazy {
            User(
                id = 1001,
                name = "Eduardo Santos",
                img = "https://randomuser.me/api/portraits/men/9.jpg",
                username = "@eduardo.santos"
            )
        }
    }
}