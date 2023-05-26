package com.example.homeless_helper_vs29.reciclerVierw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_pantallas.reciclerVierw.LugaresViewHolder
import com.example.hh_pantallas.reciclerVierw.MisOfertasViewHolder
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta


class MisOfertasRvAdapter(private val empleos:List<Oferta>): RecyclerView.Adapter<MisOfertasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MisOfertasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MisOfertasViewHolder(layoutInflater.inflate(R.layout.rv_layout_mis_oferta, parent, false))
    }

    override fun onBindViewHolder(holder: MisOfertasViewHolder, position: Int) {

        val item = empleos[position]
        val context = holder.itemView.context
        holder.printOfertas(item,context);

    }

    override fun getItemCount(): Int {
        return  empleos.size
    }


}


    /*
    MisOfertasRvAdapter

    MisOfertasViewHolder
     */
