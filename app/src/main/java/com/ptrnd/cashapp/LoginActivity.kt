package com.ptrnd.cashapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.ptrnd.cashapp.databinding.ActivityLoginBinding
import com.ptrnd.cashapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener{
            var isiUsername = edit_login_username.text.toString()
            var isiPassword = edit_login_password.text.toString()

            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.readSpecificUserVM(isiUsername, isiPassword).observe(this){
                for (item in it){
                    if (isiUsername == item.username && isiPassword == item.password){
                        val intent = Intent(this, HomeActivity::class.java)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                        break
                    } else {
                        Toast.makeText(this, "username dan/atau password salah atau belum diisi.", Toast.LENGTH_LONG).show()
                    }
                }
            }


        }
    }

}