package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.*
import com.picpay.desafio.android.helper.CACHE_DURATION
import com.picpay.desafio.android.model.DataManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.UnknownHostException


class UserViewModel (private val dataManager: DataManager) : ViewModel(){

    private suspend fun doUpdateCache(){
        if(shouldUpdateUsersCache())
            fetchRecentPlants()
    }

    suspend fun fetchRecentPlants(){
        val users = dataManager.userService.getUsers()
        users?.let {
            dataManager.userRepository.doInsertAll(it)
        }
    }

    fun shouldUpdateUsersCache() : Boolean {
        return if(System.currentTimeMillis() - dataManager.currentCacheTime > CACHE_DURATION){
            dataManager.currentCacheTime = System.currentTimeMillis()
            true
        }else{
            false
        }
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