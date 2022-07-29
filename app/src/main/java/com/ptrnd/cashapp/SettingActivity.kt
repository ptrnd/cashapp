package com.ptrnd.cashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ptrnd.cashapp.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpanSetting.setOnClickListener {
            Toast.makeText(this, "Data Berhasil Disimpan.", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.btnBackSetting.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}