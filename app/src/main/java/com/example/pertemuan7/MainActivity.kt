package com.example.pertemuan7

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.pertemuan7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTiketBTR.setOnClickListener {
            val intent = Intent(this,InputDataActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnTiketMTR.setOnClickListener {
            val intent = Intent(this,InputDataActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnTiketMKB.setOnClickListener {
            val intent = Intent(this,InputDataActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.imageProfile.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
            finish()
        }





    }
}