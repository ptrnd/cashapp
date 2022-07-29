package com.ptrnd.cashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ptrnd.cashapp.databinding.ActivityDetailFlowBinding
import com.ptrnd.cashapp.viewmodel.FlowViewModel
import kotlinx.coroutines.flow.flow

class DetailFlowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFlowBinding
    private lateinit var mFlowViewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implementasi recycleView
        val adapter = ListAdapter()
        val recyclerView = binding.rvDetailFlow
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        mFlowViewModel = ViewModelProvider(this).get(FlowViewModel::class.java)
        mFlowViewModel.readAllData().observe(this, Observer {
//            Log.d("panjang data", it.size.toString())
            adapter.setData(it)
        })

        binding.btnBackDetail.setOnClickListener {
            finish()
        }
    }
}