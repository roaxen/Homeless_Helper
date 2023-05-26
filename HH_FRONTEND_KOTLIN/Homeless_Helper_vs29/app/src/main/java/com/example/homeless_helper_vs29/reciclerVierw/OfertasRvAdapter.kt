package com.example.hh_pantallas.reciclerVierw


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta


class OfertasRvAdapter(private val empleos:List<Oferta>): RecyclerView.Adapter<OfertasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfertasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OfertasViewHolder(layoutInflater.inflate(R.layout.rv_layout_oferta, parent, false))
    }

    override fun onBindViewHolder(holder: OfertasViewHolder, position: Int) {
        holder.printOfertas(empleos[position])

    }

    override fun getItemCount(): Int {
    return  empleos.size
    }


}
