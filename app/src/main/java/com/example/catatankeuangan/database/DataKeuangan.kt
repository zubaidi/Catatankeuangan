package com.example.catatankeuangan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_keuangan")
data class DataKeuangan(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "keterangan")
    var keterangan: String,
    @ColumnInfo(name = "kategori")
    var kategori: String,
    @ColumnInfo(name = "nominal")
    var nominal: Int,
    @ColumnInfo(name = "tanggal")
    var tanggal: String
)
