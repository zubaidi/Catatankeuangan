package com.example.catatankeuangan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catatankeuangan.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var bindEdit: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindEdit = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindEdit.root)
        bindEdit.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}