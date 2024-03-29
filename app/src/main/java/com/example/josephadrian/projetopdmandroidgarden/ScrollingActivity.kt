package com.example.josephadrian.projetopdmandroidgarden

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {
    private lateinit var ivPlanta: ImageView
    private lateinit var tvNomePlanta: TextView
    private lateinit var tvCategoriaPlanta: TextView
    private lateinit var tvDescricaoPlanta: TextView
    private lateinit var btApagar: FloatingActionButton
    private lateinit var p: Planta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        this.ivPlanta = findViewById(R.id.ivScrolling)
        this.tvNomePlanta = findViewById(R.id.tvScrollingNome)
        this.tvCategoriaPlanta = findViewById(R.id.tvScrollingCategoria)
        this.tvDescricaoPlanta = findViewById(R.id.tvScrollingDescricao)
        this.btApagar = findViewById(R.id.btScrollApagar)
        this.btApagar.setOnClickListener({Apagar(it)})
        this.p = intent.getSerializableExtra("PLANTA") as Planta
        val planta = intent.getSerializableExtra("PLANTA")
        //setar imagem aqui
        //  ---------
        //
        this.tvNomePlanta.text = (planta as Planta).nome
        this.tvCategoriaPlanta.text = planta.categoria
        this.tvDescricaoPlanta.text = planta.descricao

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun Apagar(view: View){

            //val itResp = Intent()

            //itResp.putExtra("PLANTA", this.p)
           // setResult(Activity.RESULT_OK)
            finish()


    }
}
