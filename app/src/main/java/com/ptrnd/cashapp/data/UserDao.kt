package com.ptrnd.cashapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    //class berisi perintah crud sql untuk mengakses tabel user database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user ORDER BY id_user ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun readSpecificUser(username: String, password: String): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id_user = :id_user")
    fun readUserById(id_user: Int): LiveData<List<User>>
}