package com.example.catatankeuangan.adapter

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catatankeuangan.R
import com.example.catatankeuangan.database.DataKeuangan

class DataAdapter(var list: ArrayList<DataKeuangan>) : RecyclerView.Adapter<DataAdapter.DataHolder>(){
    class DataHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dataKeterangan = itemView.findViewById<TextView>(R.id.txtDataKeterangan)
        var dataSaldo = itemView.findViewById<TextView>(R.id.txtDataSaldo)
        var dataSaldoKeterangan = itemView.findViewById<TextView>(R.id.txtDataSaldoKet)
        var dataTanggal = itemView.findViewById<TextView>(R.id.txtDataTanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_data, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.dataKeterangan.text = list[position].keterangan
        holder.dataSaldo.text = list[position].nominal.toString()
        holder.dataTanggal.text = list[position].tanggal
        holder.dataSaldoKeterangan.text = list[position].kategori
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<DataKeuangan>) {
        list.clear()
        list.addAll(newList)
    }
}