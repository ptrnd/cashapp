package com.ptrnd.cashapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ptrnd.cashapp.data.CashAppDatabase
import com.ptrnd.cashapp.data.User
import com.ptrnd.cashapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAlldata:LiveData<List<User>>
    private val repository:UserRepository

    init {
        val userDao = CashAppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAlldata = repository.readAllData()
    }

    fun addUser(user: User){
        // menjalankan code di dalam background aplikasi
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun readSpecificUserVM(username: String, password: String) = repository.readSpecificUserRepo(username, password)
}