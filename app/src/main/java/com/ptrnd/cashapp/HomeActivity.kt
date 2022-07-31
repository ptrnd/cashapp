package com.ptrnd.cashapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.databinding.ActivityHomeBinding
import com.ptrnd.cashapp.viewmodel.FlowViewModel
import java.text.NumberFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mFlowViewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFlowViewModel = ViewModelProvider(this).get(FlowViewModel::class.java)

        binding.tvPengeluaran.setText("Rp. ")
        uangMasuk()
        uangKeluar()

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

    private fun uangMasuk() {
        mFlowViewModel.readtipeFlowVM("Pemasukan").observe(this) {
            var total = 0.0f
            for (item in it) {
                total += item.pemasukan
            }
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            val rupiah = numberFormat.format(total).toString()
            binding.tvPemasukan.setText(rupiah)
        }

    }

    private fun uangKeluar(){
        mFlowViewModel.readtipeFlowVM("Pengeluaran").observe(this) {
            var total = 0.0f
            for (item in it) {
                total += item.pengeluaran
            }
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            val rupiah = numberFormat.format(total).toString()
            binding.tvPengeluaran.setText(rupiah)
        }
    }
}