package com.example.josephadrian.projetopdmandroidgarden

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Room
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    val CADASTRO = 1
    val ATUALIZAR = 2
    var POSITION_EDIT = -1
    private lateinit var plantaDao: PlantaDAO
    private lateinit var adapter: PlantaAdapter
    private lateinit var lvPlantas: GridView
    private lateinit var lista: ArrayList<Planta>
    private lateinit var btCadastrar: Button
    private lateinit var telaReceiver: TelaReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCadastrar = findViewById(R.id.btMainCadastrar)
        this.btCadastrar.setOnClickListener({cadastrar(it)})

        this.telaReceiver = TelaReceiver()

        val database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "planta-database").allowMainThreadQueries().build()

        plantaDao = database.plantaDAO()
        this.lista = plantaDao.all() as ArrayList<Planta>

        this.lvPlantas = findViewById(R.id.gvMainPlanta)
        this.lvPlantas.adapter = PlantaAdapter(this, this.lista as ArrayList<Planta>)

        this.lvPlantas.setOnItemClickListener(ClickList())
        this.lvPlantas.setOnItemLongClickListener(LongClickList())
    }

    override fun onResume() {
        super.onResume()

        val itfTela = IntentFilter()
        itfTela.addAction(Intent.ACTION_USER_PRESENT)
        registerReceiver(this.telaReceiver, itfTela)
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(this.telaReceiver)
    }

    fun cadastrar(view: View){
        val it = Intent(this, FormActivity::class.java)
        startActivityForResult(it, CADASTRO)
    }
    inner class ClickList : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val planta = this@MainActivity.lista.get(position) as Planta

            val it = Intent(this@MainActivity, ScrollingActivity::class.java)
            it.putExtra("PLANTA", planta)
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
                val it = Intent(this@MainActivity, FormActivity::class.java)
                var planta = this@MainActivity.lista[position]
                it.putExtra("PLANTA", planta)
                this@MainActivity.POSITION_EDIT = position
                startActivityForResult(it, ATUALIZAR)


            return true
        }




    }

    fun atualizar(){
        this.lista.clear()
        this.lista.addAll(this.plantaDao.all())
        (this.lvPlantas.adapter as PlantaAdapter).update()
    }

    fun updatePlanta(id: Int, nome: String, categoria: String) {
        val planta = plantaDao.getPlanta(id)
        planta.nome = nome
        planta.categoria = categoria
        plantaDao.updatePlanta(planta)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CADASTRO){
                val p = data?.getSerializableExtra("PLANTA") as Planta


                this.plantaDao.add(p)

                this.atualizar()
            }else if(requestCode == ATUALIZAR){
                val p = data?.getSerializableExtra("PLANTA") as Planta

                this.updatePlanta(POSITION_EDIT + 1, p.nome, p.categoria)
                this.atualizar()
            }
        }
    }
}
