package com.example.josephadrian.projetopdmandroidgarden

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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



        this.etNome = findViewById(R.id.etFormNome)
        this.etCategoria = findViewById(R.id.etFormCategoria)
        this.btEnviar = findViewById(R.id.btFormEnviar)

        this.btEnviar.setOnClickListener(Onclick())

        var planta = intent.getSerializableExtra("PLANTA")
        if(planta != null){
            this.etNome.text.append((planta as Planta).nome)
            this.etCategoria.text.append((planta as Planta).categoria)
        }



    }

    inner class Onclick: View.OnClickListener{
        override fun onClick(v: View?) {
            if(this@FormActivity.etNome.text.isBlank() or this@FormActivity.etCategoria.text.isBlank()){
                val msg = "preencha todos os campos!"
                Toast.makeText(this@FormActivity, msg, Toast.LENGTH_LONG).show()
            }else {

                val nome = this@FormActivity.etNome.text.toString()
                val categoria = this@FormActivity.etCategoria.text.toString()
                val planta = Planta(nome, categoria)


                val itResp = Intent()
                itResp.putExtra("PLANTA", planta)
                setResult(Activity.RESULT_OK, itResp)

                finish()
            }

        }

    }

}
