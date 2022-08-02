package com.ptrnd.cashapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.databinding.ActivityPengeluaranBinding
import com.ptrnd.cashapp.viewmodel.FlowViewModel
import kotlinx.android.synthetic.main.activity_pemasukan.*
import kotlinx.android.synthetic.main.activity_pengeluaran.*
import kotlinx.android.synthetic.main.flow_card.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class PengeluaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengeluaranBinding
    private lateinit var mFlowViewModel: FlowViewModel

    private var current: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPengeluaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pengaturan tanggal
        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            calFormatEdit(calendar)
        }

        binding.nominalPengeluaran.doOnTextChanged {
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int ->
            if (s.toString() != current){
//                nominal_pemasukan.removeTextChangedListener(this)

                val cleanString: String = s!!.replace("""[Rp,.]""".toRegex(), "")

                val parsed = cleanString.toDouble()
                val formatted = NumberFormat.getCurrencyInstance().format((parsed / 100))

                current = formatted
                nominal_pengeluaran.setText(formatted)
                nominal_pengeluaran.setSelection(formatted.length)

//                nominal_pengeluaran.addTextChangedListener(this)
            }
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
        var pemasukan = 0
        var pengeluaran = nominal_pengeluaran.text.toString()
        val tipe = "Pengeluaran"
        val keterangan = keterangan_pengeluaran.text.toString()

        // menghapus rupiah untuk disimpan di database
        pengeluaran = pengeluaran.replace("""[Rp,.]""".toRegex(), "")

        if (cekInput(tanggal, (pengeluaran.toFloat() / 100), tipe, keterangan)){
            //membuat object flow
            val flow = Flow(0, tanggal, (pemasukan.toFloat() / 100), (pengeluaran.toFloat() / 100), tipe, keterangan)

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