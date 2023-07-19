package com.example.pertemuan7

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan7.databinding.ActivityHistoryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList


class HistoryActivity : AppCompatActivity(){
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private var modelDatabaseList: MutableList<ModelDatabase> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inisialisasi RecyclerView dengan adapter dan layout manager
        adapter = HistoryAdapter(modelDatabaseList)
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        // Cek apakah daftar kosong atau tidak untuk menampilkan TextView tvNotFound
        if (modelDatabaseList.isEmpty()) {
            binding.tvNotFound.visibility = View.VISIBLE
        } else {
            binding.tvNotFound.visibility = View.GONE
        }

        // Dapatkan data dari Firebase Realtime Database
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("Travel")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Clear modelDatabaseList sebelum mengisi ulang data
                modelDatabaseList.clear()

                // Loop melalui setiap child data yang ada di Firebase
                for (childSnapshot in dataSnapshot.children) {
                    // Ambil data dari Firebase dan tambahkan ke modelDatabaseList
                    val data = childSnapshot.getValue(ModelDatabase::class.java)
                    data?.let { modelDatabaseList.add(it) }
                }

                // Notifikasi adapter bahwa data telah berubah
                adapter.setData(modelDatabaseList)

                // Cek apakah daftar kosong atau tidak untuk menampilkan TextView tvNotFound
                if (modelDatabaseList.isEmpty()) {
                    binding.tvNotFound.visibility = View.VISIBLE
                } else {
                    binding.tvNotFound.visibility = View.GONE
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@HistoryActivity, "Gagal mendapatkan data dari Firebase", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}