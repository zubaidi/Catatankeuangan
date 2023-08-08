package com.example.catatankeuangan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataKeuangan::class], version = 1, exportSchema = false)
abstract class DataDB : RoomDatabase(){

    abstract fun dataDAO() : DataDAO

    companion object {
        @Volatile
        private var instance : DataDB ?= null
        private val KEY = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(KEY) {
            instance ?: buildDatabase(context).also{
                instance =it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DataDB::class.java,
            "db_keuangan"
        ).fallbackToDestructiveMigration().build()

    }

}