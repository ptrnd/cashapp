package com.ptrnd.cashapp.repository

import androidx.lifecycle.LiveData
import com.ptrnd.cashapp.data.User
import com.ptrnd.cashapp.data.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}