package com.example.catatankeuangan

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catatankeuangan.database.DataDB
import com.example.catatankeuangan.database.DataKeuangan
import com.example.catatankeuangan.databinding.ActivityEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private lateinit var bindEdit: ActivityEditBinding
    private val db by lazy { DataDB(this) }
    private var dataId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindEdit = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindEdit.root)
        bindEdit.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        this.getDataKeuangan()
    }

    private fun getDataKeuangan() {
        dataId = intent.getIntExtra("data_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val datas = db.dataDAO().getAllDataByID(dataId)[0]
            bindEdit.txtInputNominal.setText(datas.nominal.toString())
            bindEdit.txtInputKeterangan.setText(datas.keterangan)
            bindEdit.txtInputTanggal.setText(datas.tanggal)
            if ( datas.kategori == "Masuk" ) {
                bindEdit.rbMasuk.isChecked
            } else {
                bindEdit.rbKeluar.isChecked
            }
        }
    }
}