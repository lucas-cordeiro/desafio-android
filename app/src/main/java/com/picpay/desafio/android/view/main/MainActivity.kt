package com.picpay.desafio.android.view.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.view.main.adapter.UserListAdapter
import com.picpay.desafio.android.model.data.User
import com.picpay.desafio.android.model.remote.UserService
import com.picpay.desafio.android.view.base.BaseActivity
import com.picpay.desafio.android.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.observe
import com.picpay.desafio.android.helper.visible
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    @Inject
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        progressBar.visibility = View.VISIBLE

        viewModel.users.observe(this){
            recyclerView.visible = true
            adapter.users = it
        }
        viewModel.loading.observe(this){
            progressBar.visible = it
        }

        viewModel.erroToast.observe(this){
            recyclerView.visible = false
            val message = it?:getString(R.string.error)
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
