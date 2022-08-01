package com.ptrnd.cashapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.databinding.ActivityHomeBinding
import com.ptrnd.cashapp.viewmodel.FlowViewModel
import kotlinx.android.synthetic.main.activity_pemasukan.*
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mFlowViewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFlowViewModel = ViewModelProvider(this).get(FlowViewModel::class.java)

        binding.btnPemasukan.setOnClickListener {
            val intent = Intent(this, PemasukanActivity::class.java)
            startActivity(intent)
        }

        binding.btnPengeluaran.setOnClickListener {
            val intent = Intent(this, PengeluaranActivity::class.java)
            startActivity(intent)
        }

        binding.btnDetail.setOnClickListener {
            val intent = Intent(this, DetailFlowActivity::class.java)
            startActivity(intent)
        }

        binding.btnSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        uangMasuk()
        uangKeluar()
    }

    private fun uangMasuk() {
        val tanggalsaiki: Date = Date()
        val bulaniki = tanggalsaiki.month

        mFlowViewModel.readtipeFlowVM("Pemasukan").observe(this) {
            var filteredDate = it.filter { flow ->
                val formatTgl = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(formatTgl, Locale.getDefault())
                val date = sdf.parse(flow.tanggal)

                date.month == bulaniki
            }

            var total = 0.0f
            for (item in filteredDate) {
                total += item.pemasukan
            }
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            val rupiah = numberFormat.format(total).toString()
            binding.tvPemasukan.setText(rupiah)
        }

    }

    private fun uangKeluar(){
        val tanggalsaiki: Date = Date()
        val bulaniki = tanggalsaiki.month

        mFlowViewModel.readtipeFlowVM("Pengeluaran").observe(this) {
            var filteredDate = it.filter { flow ->
                val formatTgl = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(formatTgl, Locale.getDefault())
                val date = sdf.parse(flow.tanggal)

                date.month == bulaniki
            }

            var total = 0.0f
            for (item in filteredDate) {
                total += item.pengeluaran
            }
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            val rupiah = numberFormat.format(total).toString()
            binding.tvPengeluaran.setText(rupiah)
        }
    }
}