package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface PlantaDAO {

    @Query("SELECT * FROM planta")
    fun all(): List<Planta>

    @Insert
    fun add(vararg planta: Planta)

    @Query("SELECT * FROM planta WHERE id == :id")
    fun getPlanta(id: Int): Planta

    @Update
    fun updatePlanta(planta: Planta): Int


}