package com.example.catatankeuangan

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatankeuangan.adapter.DataAdapter
import com.example.catatankeuangan.database.DataDB
import com.example.catatankeuangan.database.DataKeuangan
import com.example.catatankeuangan.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.invoke.ConstantCallSite
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
        // set adapter dan edit data
        adapter = DataAdapter(arrayListOf(),
            object : DataAdapter.OnAdapterListener {
                override fun onClick(dataKeuangan: DataKeuangan) {
                    intentEdit(Constant.TYPE_READ, dataKeuangan.id)
                }

                override fun onDelete(dataKeuangan: DataKeuangan) {
                    deleteAlert(dataKeuangan)
                }
            })
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

    private fun intentEdit(intent_type: Int, data_id: Int) {
        startActivity(
            Intent(this, EditActivity::class.java)
                .putExtra("intent_type", intent_type)
                .putExtra("data_id", data_id)
        )
    }

    private fun deleteAlert(dataKeuangan: DataKeuangan) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("Yakin akan hapus data ${ dataKeuangan.keterangan }?")
            setNegativeButton("Batal") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface: DialogInterface, i: Int ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.dataDAO().deleteData(dataKeuangan)
                    dialogInterface.dismiss()
                    setLayoutData()
                }
            }
        }
        dialog.show()
    }

    fun setTanggal() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        binding.txtTanggalSekarang.text = dateTime
    }

}
