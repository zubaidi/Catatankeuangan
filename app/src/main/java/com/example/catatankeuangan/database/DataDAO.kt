package com.example.catatankeuangan.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataKeuangan: DataKeuangan)

    @Update
    suspend fun updateData(dataKeuangan: DataKeuangan)

    @Delete
    suspend fun deleteData(dataKeuangan: DataKeuangan)

    @Query("SELECT * FROM tb_keuangan")
    fun getAllData() : List<DataKeuangan>

    @Query("SELECT * FROM tb_keuangan WHERE kategori = 'Masuk'")
    fun getAllDataMasuk() : List<DataKeuangan>

    @Query("SELECT * FROM tb_keuangan WHERE kategori = 'Keluar'")
    fun getAllDataKeluar() : List<DataKeuangan>

    @Query("SELECT * FROM tb_keuangan WHERE id =:data_id")
    fun getAllDataByID(data_id:Int) : List<DataKeuangan>
}