package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.media.Image
import java.io.Serializable
import java.sql.Blob

@Entity
class Planta: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var nome: String
    var categoria:String
    //var descricao: String


    constructor(nome: String, categoria: String /*,descricao: String*/){
        this.nome = nome
        this.categoria = categoria
        //this.descricao = descricao
    }

}