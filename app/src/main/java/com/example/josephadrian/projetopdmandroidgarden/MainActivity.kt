package com.example.josephadrian.projetopdmandroidgarden

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var plantaDao: PlantaDAO
    private lateinit var adapter: PlantaAdapter
    private lateinit var lvPlantas: ListView
    private lateinit var lista: List<Planta>
    private lateinit var btCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCadastrar = findViewById(R.id.btMainCadastrar)
        this.btCadastrar.setOnClickListener({
            val it = Intent(this, FormActivity::class.java)
            startActivity(it)
        })

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "planta-database").allowMainThreadQueries().build()

        plantaDao = database.plantaDAO()
        this.lista = plantaDao.all()

        this.lvPlantas = findViewById(R.id.lvMainPlanta)
        this.lvPlantas.adapter = PlantaAdapter(this, this.lista as ArrayList<Planta>)

        this.lvPlantas.setOnItemClickListener(ClickList())
        this.lvPlantas.setOnItemLongClickListener(LongClickList())
    }
    inner class ClickList : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val planta = this@MainActivity.lista.get(position) as Planta

            val it = Intent(this@MainActivity, ScrollingActivity::class.java)
            val id = planta.id
            it.putExtra("ID", id)
           // Log.i("TESTE", "")
            startActivity(it)

        }
    }

    inner class LongClickList : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
           // val pessoa = this@MainActivity.lista.get(position)
            //this@MainActivity.dao.delete(pessoa.id)
           // this@MainActivity.atualizar()
            return true
        }

    }
}
