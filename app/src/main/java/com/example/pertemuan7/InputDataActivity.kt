package com.example.pertemuan7

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan7.databinding.ActivityInputDataBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class InputDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputDataBinding
    private lateinit var dbHelper: UserDbHelper
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var hargaPerOrangDewasa = 100000 // Ganti dengan harga tiket per orang dewasa
    private var hargaPerOrangAnak = 50000 // Ganti dengan harga tiket per orang anak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHelper = UserDbHelper(this)

        // Data tempat wisata untuk spinner
        val dataWisata = listOf("Wisata A", "Wisata B", "Wisata C")

        // Inisialisasi spinner dengan data tempat wisata
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataWisata)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spWisata.adapter = adapter

        // Tambahkan listener untuk menghitung total harga tiket saat jumlah pengunjung berubah
        binding.imageMinus1.setOnClickListener {
            decreaseJumlahAnak()
            hitungTotalHarga()
        }
        binding.imageAdd1.setOnClickListener {
            increaseJumlahAnak()
            hitungTotalHarga()
        }
        binding.imageMinus2.setOnClickListener {
            decreaseJumlahDewasa()
            hitungTotalHarga()
        }
        binding.imageAdd2.setOnClickListener {
            increaseJumlahDewasa()
            hitungTotalHarga()
        }

        // Panggil hitungTotalHarga saat jumlah pengunjung anak atau dewasa berubah melalui spinner
        binding.spWisata.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                hitungTotalHarga()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Inisialisasi Firebase Auth dan Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Tambahkan OnClickListener untuk memilih tanggal berangkat menggunakan MaterialDatePicker
        binding.inputTanggal.setOnClickListener {
            showDatePicker()
        }

        binding.btnCheckout.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val telepon = binding.inputTelepon.text.toString()
            val totalHarga = hitungTotalHarga()

            // Simpan data ke Firebase
            val currentUser = auth.currentUser
            currentUser?.uid?.let { userId ->
                val data = HashMap<String, Any>()
                data["nama"] = nama
                data["telepon"] = telepon
                data["tempatWisata"] = binding.spWisata.selectedItem.toString()
                data["tanggalBerangkat"] = binding.inputTanggal.text.toString()
                data["jumlahAnak"] = binding.tvJmlAnak.text.toString().toInt()
                data["jumlahDewasa"] = binding.tvJmlDewasa.text.toString().toInt()
                data["totalHarga"] = totalHarga

                database.child("users").child(userId).setValue(data)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data berhasil disimpan di Firebase!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal menyimpan data di Firebase!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private var jumlahAnak = 0
    private fun decreaseJumlahAnak() {
        if (jumlahAnak > 0) {
            jumlahAnak--
            binding.tvJmlAnak.text = jumlahAnak.toString()
        }
    }

    private fun increaseJumlahAnak() {
        jumlahAnak++
        binding.tvJmlAnak.text = jumlahAnak.toString()
    }

    private var jumlahDewasa = 0
    private fun decreaseJumlahDewasa() {
        if (jumlahDewasa > 0) {
            jumlahDewasa--
            binding.tvJmlDewasa.text = jumlahDewasa.toString()
        }
    }

    private fun increaseJumlahDewasa() {
        jumlahDewasa++
        binding.tvJmlDewasa.text = jumlahDewasa.toString()
    }

    private val datePicker: MaterialDatePicker<Long> by lazy {
        MaterialDatePicker.Builder.datePicker().build()
    }

    private fun showDatePicker() {
        datePicker.addOnPositiveButtonClickListener {
            val selectedDate = Date(it ?: 0)

            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(selectedDate)
            binding.inputTanggal.setText(formattedDate)
            hitungTotalHarga()
        }
        datePicker.show(supportFragmentManager, datePicker.toString())
    }

    private fun hitungTotalHarga(): Int {
        val jumlahWisatawanAnak = binding.tvJmlAnak.text.toString().toInt()
        val jumlahWisatawanDewasa = binding.tvJmlDewasa.text.toString().toInt()
        val hargaWisataAnak = jumlahWisatawanAnak * hargaPerOrangAnak
        val hargaWisataDewasa = jumlahWisatawanDewasa * hargaPerOrangDewasa
        val totalHarga = hargaWisataAnak + hargaWisataDewasa
        // Tampilkan total harga tiket ke layar
        binding.tvTotalHarga.text = "Total Harga Tiket: Rp. $totalHarga"
        return totalHarga
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