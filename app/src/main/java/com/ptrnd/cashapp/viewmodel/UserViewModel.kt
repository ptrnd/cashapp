package com.ptrnd.cashapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ptrnd.cashapp.data.CashAppDatabase
import com.ptrnd.cashapp.data.User
import com.ptrnd.cashapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
//    menyediakan data user kepada UI
    private val readAlldata:LiveData<List<User>>
    private val repository:UserRepository

    init {
        val userDao = CashAppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAlldata = repository.readAllData()
    }

    fun readSpecificUserVM(username: String, password: String) = repository.readSpecificUserRepo(username, password)

    fun readUserByIdVM(id_user: Int) = repository.readUserByIdRepo(id_user)

    fun addUser(user: User){
        // menjalankan code di dalam background aplikasi
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUserVM(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUserRepo(user)
        }
    }
}