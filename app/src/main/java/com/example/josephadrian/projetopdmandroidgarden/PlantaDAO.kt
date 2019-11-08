package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlantaDAO {

    @Query("SELECT * FROM planta")
    fun all(): List<Planta>

    @Insert
    fun add(vararg planta: Planta)
}