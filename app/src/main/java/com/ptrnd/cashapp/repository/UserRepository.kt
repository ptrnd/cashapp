package com.ptrnd.cashapp.repository

import androidx.lifecycle.LiveData
import com.ptrnd.cashapp.data.User
import com.ptrnd.cashapp.data.UserDao

class UserRepository(private val userDao: UserDao) {
//    pembuatan arsitektur class untuk mengakses data pada database
//    val readAllData: LiveData<List<User>> = userDao.readAllData()
    fun readAllData(): LiveData<List<User>>{
        return userDao.readAllData()
    }

    fun readSpecificUserRepo(username: String, password: String): LiveData<List<User>>{
        return userDao.readSpecificUser(username, password)
    }

    fun readUserByIdRepo(id_user: Int): LiveData<List<User>>{
        return userDao.readUserById(id_user)
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun updateUserRepo(user: User){
        userDao.updateUser(user)
    }
}