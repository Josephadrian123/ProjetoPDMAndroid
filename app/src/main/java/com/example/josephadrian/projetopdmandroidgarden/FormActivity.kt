package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    private lateinit var etNome: EditText
    private lateinit var etCategoria: EditText
    private lateinit var btEnviar: Button
    private lateinit var plantaDao: PlantaDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setSupportActionBar(toolbar)

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "planta-database").allowMainThreadQueries().build()

        plantaDao = database.plantaDAO()

        this.etNome = findViewById(R.id.etFormNome)
        this.etCategoria = findViewById(R.id.etFormCategoria)
        this.btEnviar = findViewById(R.id.btFormEnviar)

        this.btEnviar.setOnClickListener(Onclick())



    }

    inner class Onclick: View.OnClickListener{
        override fun onClick(v: View?) {
            val nome = this@FormActivity.etNome.text.toString()
            val categoria = this@FormActivity.etCategoria.text.toString()
            val planta = Planta(nome, categoria)
            plantaDao.add(planta)

            finish()

        }

    }

}
