package com.ptrnd.cashapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    //class berisi perintah crud untuk mengakses tabel flow database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id_user ASC")
    fun readAllData(): LiveData<List<User>>
}