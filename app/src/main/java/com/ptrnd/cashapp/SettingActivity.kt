package com.ptrnd.cashapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ptrnd.cashapp.data.User
import com.ptrnd.cashapp.databinding.ActivitySettingBinding
import com.ptrnd.cashapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var mUserViewModel: UserViewModel
    lateinit var sharedPref: SharedPreferences

    private val namaSF = "login_data"
    private var oldPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPref = getSharedPreferences(namaSF, Context.MODE_PRIVATE)
        setContentView(binding.root)

        val id_user = sharedPref.getInt("key.id", 0)
        mUserViewModel.readUserByIdVM(id_user).observe(this){
            oldPassword = it.first().password
        }

        binding.btnSimpanSetting.setOnClickListener {
            val passLama = edit_old_password.text.toString()
            val passBaru = edit_new_password.text.toString()
            val passConfirm = edit_confirm_new_password.text.toString()

            updateData(passLama, passBaru, passConfirm)
        }

        binding.btnBackSetting.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun updateData(oldPassword: String, newPassword: String, confirmPass: String){
        if (!cekItem(oldPassword, newPassword, confirmPass)){
            Toast.makeText(this, "Mohon diisi semua isiannya.", Toast.LENGTH_LONG).show()
        } else if (!cekPasswordSama(newPassword, confirmPass)){
            Toast.makeText(this, "Password baru dan konfirmasi password tidak sama.", Toast.LENGTH_LONG).show()
        } else if (!cekPasswordLama(oldPassword)){
            Toast.makeText(this, "Password lama salah.", Toast.LENGTH_LONG).show()
        } else {
            val idUser = sharedPref.getInt("key.id", 0)
            val username = sharedPref.getString("key.username", null).toString()
            val updatedUser = User(idUser, username, newPassword)
            mUserViewModel.updateUserVM(updatedUser)
            Toast.makeText(this, "Password baru telah disimpan.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun cekItem(oldPassword: String, newPassword: String, confirmPassword: String): Boolean{
        return !(TextUtils.isEmpty(oldPassword) &&
                TextUtils.isEmpty(newPassword) &&
                TextUtils.isEmpty(confirmPassword))
    }

    private fun cekPasswordSama(newPassword: String, confirmPassword: String): Boolean{
        return newPassword == confirmPassword
    }

    private fun cekPasswordLama(oldPassword: String): Boolean{
        return this.oldPassword == oldPassword
    }
}