package com.example.pertemuan7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan7.databinding.ListItemHistoryBinding
import com.example.pertemuan7.ModelDatabase



class HistoryAdapter(var modelDatabase: MutableList<ModelDatabase>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var modelDatabaseList: List<ModelDatabase> = emptyList()

    fun setData(dataList: List<ModelDatabase>) {
        modelDatabaseList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelDatabaseList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return modelDatabaseList.size
    }

    inner class ViewHolder(private val binding: ListItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDatabase) {
            // Bind data to views in the list item layout
            binding.tvNama.text = data.namaPenumpang
            binding.tvTelepon.text = data.telepon
            binding.tvTempatWisata.text = data.tempatWisata
            binding.tvJumlahAnak.text = data.jumlahAnak.toString()
            binding.tvJumlahDewasa.text = data.jumlahDewasa.toString()
            binding.tvTanggalBerangkat.text = data.tanggalBerangkat
            binding.tvTotalHarga.text = "Total Harga Tiket: Rp. ${data.totalHarga}"

            // Add any other bindings you need here
        }
    }
}
