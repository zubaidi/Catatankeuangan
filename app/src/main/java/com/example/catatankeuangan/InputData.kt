package com.example.catatankeuangan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.catatankeuangan.adapter.DataAdapter
import com.example.catatankeuangan.database.DataDB
import com.example.catatankeuangan.database.DataKeuangan
import com.example.catatankeuangan.databinding.ActivityInputDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class InputData : AppCompatActivity() {

    private lateinit var bindInput: ActivityInputDataBinding
    private val db by lazy { DataDB(this) }
    private lateinit var adapterData : DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindInput = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(bindInput.root)
        //adapterData = DataAdapter(arrayListOf())
        bindInput.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
        bindInput.btnSimpan.setOnClickListener {
            this.validasiInput()
            this.simpanData()
        }
        this.setTangalPengeluaran()
    }

    private fun validasiInput() {
        // validasi
        if (bindInput.txtInputNominal.text.isEmpty()) {
            Toast.makeText(this, "Masukkan nominal", Toast.LENGTH_LONG).show()
        } else if (bindInput.txtInputKeterangan.text.isEmpty()) {
            Toast.makeText(this, "Masukkan keterangan", Toast.LENGTH_LONG).show()
        }
    }

    private fun setTangalPengeluaran() {
        this.setTanggal()
        bindInput.txtInputTanggal.setOnClickListener {
            var cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { picker, tahun, bulan, tanggal ->
                bindInput.txtInputTanggal.setText(""+tanggal+"-"+bulan+"-"+tahun)
            }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun setTanggal() {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("d-M-yyyy")
        val dateTime = simpleDateFormat.format(calendar.time)
        bindInput.txtInputTanggal.setText(dateTime)
    }

    private fun simpanData() {
        var ketNom = ""
        if (bindInput.rbMasuk.isChecked) {
            ketNom = "Masuk"
        } else {
            ketNom = "Keluar"
        }
        CoroutineScope(Dispatchers.IO).launch {
            db.dataDAO().insertData(
                DataKeuangan(
                    bindInput.txtInputKeterangan.text.toString(),
                    ketNom,
                    bindInput.txtInputNominal.text.toString().toInt(),
                    bindInput.txtInputTanggal.text.toString()
                )
            )
            finish()
        }
    }
}