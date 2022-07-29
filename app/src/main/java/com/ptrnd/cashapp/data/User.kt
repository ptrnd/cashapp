package com.ptrnd.cashapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    //untuk pengaturan tabel user di dalam database
    @PrimaryKey(autoGenerate = true)
    val id_user: Int,
    val username: String,
    val password: String
    )