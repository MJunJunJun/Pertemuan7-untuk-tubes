package com.example.pertemuan7

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class HistoryActivity : AppCompatActivity(){
    var modelDatabaseList: MutableList<ModelDatabase> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "DISINI NANTI MUNCUL TIKET PEMESANAN", Toast.LENGTH_SHORT).show()
    }
}