package com.ptrnd.cashapp.repository

import androidx.lifecycle.LiveData
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.data.FlowDao

class FlowRepository(private val flowDao: FlowDao) {
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