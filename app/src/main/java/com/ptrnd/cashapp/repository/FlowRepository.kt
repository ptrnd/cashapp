package com.ptrnd.cashapp.repository

import androidx.lifecycle.LiveData
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.data.FlowDao
import java.util.*

class FlowRepository(private val flowDao: FlowDao) {
//    pembuatan arsitektur class untuk mengakses data pada database
//    val readAllData: LiveData<List<Flow>> = flowDao.readAllData()

    fun readAllData(): LiveData<List<Flow>>{
        return flowDao.readAllData()
    }

    fun readFlowTipeRepo(tipe: String): LiveData<List<Flow>>{
        return flowDao.readFlowTipe(tipe)
    }

    suspend fun addFlow(flow: Flow){
        flowDao.addFlow(flow)
    }
}