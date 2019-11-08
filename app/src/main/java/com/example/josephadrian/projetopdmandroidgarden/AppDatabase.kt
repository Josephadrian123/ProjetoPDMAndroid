package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Planta::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun plantaDAO(): PlantaDAO
}