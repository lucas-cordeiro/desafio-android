package com.picpay.desafio.android.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.picpay.desafio.android.model.DataManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


class UserViewModel @Inject constructor(private val dataManager: DataManager) : ViewModel(){

    private suspend fun doUpdateCache(){
        if(shouldUpdateUsersCache())
            fetchRecentPlants()
    }

    private suspend fun fetchRecentPlants(){
        val users = dataManager.userService.getUsers()
        dataManager.userRepository.doInsertAll(users)
    }

    private fun shouldUpdateUsersCache() : Boolean {
        Log.d("BUG", "currentCacheTime: ${dataManager.currentCacheTime}")
        return if(System.currentTimeMillis() - dataManager.currentCacheTime > 1000 * 60 * 1){
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
        Log.d("BUG", "init")
        launchDataLoad {
            doUpdateCache()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("BUG", "onCleared")
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