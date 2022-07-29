package com.ptrnd.cashapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flow")
data class Flow (
    //untuk pengaturan tabel flow di dalam database
    @PrimaryKey(autoGenerate = true)
    val id_flow: Int,
    val tanggal: String,
    val pemasukan: Float,
    val pengeluaran: Float,
    val tipe_flow: String,
    val keterangan: String
    )