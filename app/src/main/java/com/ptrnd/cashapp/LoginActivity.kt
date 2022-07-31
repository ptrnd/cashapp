package com.ptrnd.cashapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.ui.AppBarConfiguration
import com.ptrnd.cashapp.databinding.ActivityLoginBinding
import com.ptrnd.cashapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityLoginBinding

    private val namaSF = "login_data"
    private val KEY_ID = "key.id"
    private val KEY_USERNAME = "key.username"
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPref = getSharedPreferences(namaSF, Context.MODE_PRIVATE)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btLogin.setOnClickListener{
            var isiUsername = edit_login_username.text.toString()
            var isiPassword = edit_login_password.text.toString()
            mUserViewModel.readSpecificUserVM(isiUsername, isiPassword).observe(this){
                var loginState = false
                for (item in it){
                    if (isiUsername == item.username && isiPassword == item.password){
                        loginState = true
                        saveIdUser(item.id_user)
                        saveUsername(isiUsername)
                        break
                    } else {
                        loginState = false
                    }
                }
                if (loginState){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(LoginActivity@this, "username dan/atau password salah atau belum diisi.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun saveIdUser(id_user: Int){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_ID, id_user)
        editor.apply()
    }

    private fun saveUsername(username: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }
}