package com.ptrnd.cashapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.databinding.ActivityPengeluaranBinding
import com.ptrnd.cashapp.viewmodel.FlowViewModel
import kotlinx.android.synthetic.main.activity_pengeluaran.*
import kotlinx.android.synthetic.main.flow_card.*
import java.text.SimpleDateFormat
import java.util.*

class PengeluaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengeluaranBinding
    private lateinit var mFlowViewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPengeluaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calFormatEdit(calendar)
        }

        binding.btnTgl.setOnClickListener {
            Log.d("kepencet", "kepencet")
            DatePickerDialog(
                this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        mFlowViewModel = ViewModelProvider(this).get(FlowViewModel::class.java)

        binding.btnSimpanPengeluaran.setOnClickListener {
            insertData()
            onBackPressed()
            finish()
        }

        binding.btnBackPengeluaran.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun calFormatEdit(calendar: Calendar) {
        val formatTgl = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formatTgl, Locale.getDefault())
        date_input_pengeluaran.setText(sdf.format(calendar.time))
    }

    private fun insertData() {
        val tanggal = date_input_pengeluaran.text.toString()
        val pemasukan = 0
        val pengeluaran = nominal_pengeluaran.text.toString()
        val tipe = "Pengeluaran"
        val keterangan = keterangan_pengeluaran.text.toString()

        if (cekInput(tanggal, pengeluaran.toFloat(), tipe, keterangan)){
            //membuat object flow
            val flow = Flow(0, tanggal, pemasukan.toFloat(), pengeluaran.toFloat(), tipe, keterangan)

            //menambah data ke database
            mFlowViewModel.addFlow(flow)
            Toast.makeText(this, "Data Pengeluaran Berhasil Disimpan.",Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "mohon diisi semua kolom isinya",Toast.LENGTH_LONG).show()
        }
    }

    private fun cekInput(tanggal: String, pengeluaran: Float, tipe: String, keterangan: String): Boolean{
        return !(TextUtils.isEmpty(tanggal) &&
                pengeluaran > 0 &&
                TextUtils.isEmpty(tipe) &&
                TextUtils.isEmpty(keterangan))
    }

}