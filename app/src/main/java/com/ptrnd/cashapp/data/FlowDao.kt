package com.ptrnd.cashapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FlowDao {
//class berisi perintah crud pada sql untuk mengakses tabel flow database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFlow(flow: Flow)

    @Query("SELECT * FROM flow ORDER BY tanggal ASC")
    fun readAllData(): LiveData<List<Flow>>

    @Query("SELECT * FROM flow WHERE tipe_flow = :tipe")
    fun readFlowTipe(tipe: String): LiveData<List<Flow>>
}