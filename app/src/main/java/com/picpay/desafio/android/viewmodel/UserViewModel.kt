package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.*
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.DataManager
import com.picpay.desafio.android.model.data.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.processNextEventInCurrentThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class UserViewModel @Inject constructor(private val dataManager: DataManager) : ViewModel(){
    private suspend fun doUpdateCache(){
        if(shouldUpdateUsersCache()) fetchRecentPlants()
    }

    private suspend fun fetchRecentPlants(){
        val users = dataManager.userService.getUsers()
        dataManager.userRepository.doInsertAll(users)
    }

    private fun shouldUpdateUsersCache() : Boolean {
        return true
    }

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorToast = MutableLiveData<String?>()
    val erroToast: LiveData<String?>
        get() = _errorToast

    val users = dataManager.userRepository.doGetAll().asLiveData()

    init{
        launchDataLoad {
            doUpdateCache()
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                block()
            }catch (host: UnknownHostException){
                _errorToast.value = "Usando dados locais"
            }catch (t: Throwable){
                _errorToast.value = t.message
            }finally {
                _loading.value = false
            }
        }
    }
}