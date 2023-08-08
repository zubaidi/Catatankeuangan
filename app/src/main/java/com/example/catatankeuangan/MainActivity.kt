package com.example.catatankeuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatankeuangan.adapter.DataAdapter
import com.example.catatankeuangan.database.DataDB
import com.example.catatankeuangan.database.DataKeuangan
import com.example.catatankeuangan.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val db by lazy { DataDB(this) }
    private lateinit var adapter : DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = DataAdapter(arrayListOf())
        this.setTanggal()
        binding.btnTambahActivity.setOnClickListener {
            startActivity(Intent(this, InputData::class.java))
        }
        binding.btnPemasukan.setOnClickListener {
            this.setLayoutDataMasuk()
        }
        binding.btnPengeluaran.setOnClickListener {
            this.setLayoutDataKeluar()
        }
        binding.btnTampilSemua.setOnClickListener {
            this.setLayoutData() }
    }

    override fun onResume() {
        super.onResume()
        this.setLayoutData()
    }

    fun setLayoutData() {
        binding.listDataKeuangan.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dataDAO().getAllData()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.listDataKeuangan.adapter = adapter
    }

    fun setLayoutDataMasuk() {
        binding.listDataKeuangan.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dataDAO().getAllDataMasuk()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.listDataKeuangan.adapter = adapter
    }

    fun setLayoutDataKeluar() {
        binding.listDataKeuangan.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.dataDAO().getAllDataKeluar()
            adapter.setData(data)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
        binding.listDataKeuangan.adapter = adapter
    }

    fun setTanggal() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.txtTanggalSekarang.text = dateTime
    }
}
