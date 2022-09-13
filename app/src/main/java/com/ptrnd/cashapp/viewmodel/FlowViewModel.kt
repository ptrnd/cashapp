package com.ptrnd.cashapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ptrnd.cashapp.data.CashAppDatabase
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.repository.FlowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlowViewModel(application: Application): AndroidViewModel(application) {
//    menyediakan data flow kepada UI
    private val repository: FlowRepository

    //inisialisasi isi repository
    init {
        val flowDao = CashAppDatabase.getDatabase(application).flowDao()
        repository = FlowRepository(flowDao)
    }

    fun readAllData() = repository.readAllData()

    fun addFlow(flow: Flow){
        // menjalankan code di dalam background aplikasi
        viewModelScope.launch(Dispatchers.IO){
            repository.addFlow(flow)
        }
    }

    fun readtipeFlowVM(tipe: String) = repository.readFlowTipeRepo(tipe)
}