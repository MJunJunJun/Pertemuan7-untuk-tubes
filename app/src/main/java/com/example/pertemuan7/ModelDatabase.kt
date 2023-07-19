package com.example.pertemuan7

import java.io.Serializable
import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.google.firebase.database.IgnoreExtraProperties

@Entity(tableName = "tbl_travel")
class ModelDatabase : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Int = 0

    @ColumnInfo(name = "nama_penumpang")
    lateinit var namaPenumpang: String

    @ColumnInfo(name = "telepon")
    lateinit var telepon: String

    @ColumnInfo(name = "tempat_wisata")
    lateinit var tempatWisata: String

    @ColumnInfo(name = "jumlah_anak")
    var jumlahAnak: Int = 0

    @ColumnInfo(name = "jumlah_dewasa")
    var jumlahDewasa: Int = 0

    @ColumnInfo(name = "tanggal_berangkat")
    lateinit var tanggalBerangkat: String

    @ColumnInfo(name = "total_harga")
    var totalHarga: Int = 0

    constructor() {
        // Default constructor diperlukan untuk Firebase Realtime Database
    }

    constructor(
        namaPenumpang: String,
        telepon: String,
        tempatWisata: String,
        jumlahAnak: Int,
        jumlahDewasa: Int,
        tanggalBerangkat: String,
        totalHarga: Int
    ) {
        this.namaPenumpang = namaPenumpang
        this.telepon = telepon
        this.tempatWisata = tempatWisata
        this.jumlahAnak = jumlahAnak
        this.jumlahDewasa = jumlahDewasa
        this.tanggalBerangkat = tanggalBerangkat
        this.totalHarga = totalHarga
    }
}