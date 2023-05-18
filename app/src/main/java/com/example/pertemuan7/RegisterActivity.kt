package com.example.pertemuan7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.pertemuan7.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var dbHelper: UserDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHelper =UserDbHelper(this)
        binding.btnRegister.setOnClickListener{
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(email = email, password = password)
                dbHelper.insertUser(user)
                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"DATA tidak boleh kosong!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
            onBackPressed()
            true
            }else -> super.onOptionsItemSelected(item)
        }
    }
}