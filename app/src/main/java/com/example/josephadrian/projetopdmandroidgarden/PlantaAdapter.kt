package com.example.josephadrian.projetopdmandroidgarden

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PlantaAdapter(var context: Context, var listPlantas: ArrayList<Planta>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var layout: View
        val planta = this.listPlantas.get(position)

        if (convertView == null){
            var inflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layout = inflater.inflate(R.layout.planta_layout, null)
        }else{
            layout = convertView
        }

        val tv = layout.findViewById<TextView>(R.id.nome_planta)
        tv.text = planta.nome


        return layout
    }

    override fun getItem(position: Int): Any {
        return this.listPlantas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    override fun getCount(): Int {
        return this.listPlantas.count()
    }

    fun update(){
        notifyDataSetChanged()
    }
}