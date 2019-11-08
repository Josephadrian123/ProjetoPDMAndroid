package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Planta: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var nome: String
    var categoria:String

    constructor(nome: String, categoria: String){
        this.nome = nome
        this.categoria = categoria
    }

}