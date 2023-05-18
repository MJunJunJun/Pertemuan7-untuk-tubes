package com.example.pertemuan7

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan7.databinding.ActivityInputDataBinding




class InputDataActivity : AppCompatActivity() {
    lateinit var binding : ActivityInputDataBinding
    lateinit var dbHelper: UserDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHelper =UserDbHelper(this)
        binding.btnCheckout.setOnClickListener{
            val nama = binding.inputNama.text.toString()
            val telepon = binding.inputTelepon.text.toString()
            Toast.makeText(this, "HEHE MASIH OTW LURD MAAP BELOM BISA MUNCUL", Toast.LENGTH_SHORT).show()
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