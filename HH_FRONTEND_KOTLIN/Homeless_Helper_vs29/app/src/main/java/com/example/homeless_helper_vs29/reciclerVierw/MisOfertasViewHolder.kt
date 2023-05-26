package com.example.hh_pantallas.reciclerVierw

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Formularios.FormEditEmpleo
import com.example.homeless_helper_vs29.screen.Home
import com.example.homeless_helper_vs29.screen.ListaMisOfertasCreadas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MisOfertasViewHolder (view: View): RecyclerView.ViewHolder(view)
{

    // IDEA, PONER UN BOTON QUE ENVIE UN MENSAJE AL USUARIO QUE TIENE EL EMAIL DEL RESPONSABLE


    // WIDGET DE LA APLICACION

    val rv_titulo = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_Titulo);
    val rv_fechacreacion = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_fecha_creacion);
    val rv_precio = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_Precio);
    val rv_tiopTrabajo = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_tipoEmpleo);
    val rv_descripcion = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_Descripcion);
    val rv_localidad = view.findViewById<TextView>(R.id.rv_layout_MisOfertas_localidad);


    val rv_btn_Editar = view.findViewById<Button>(R.id.rv_layout_MisOfertas_BTN_editar);
    val rv_btn_Eliminar = view.findViewById<Button>(R.id.rv_layout_MisOfertas_BTN_eliminar);



    fun printOfertas(oferta: Oferta, context: Context) {

        rv_titulo.text =  oferta.titulo
        rv_precio.text =  oferta.precio + " /h"
        rv_descripcion.text =  oferta.descripcion
        rv_fechacreacion.text = oferta.fecha
        rv_localidad.text = oferta.localidad


        println("555555555555555555555555555555555555555555555555555555 --------- nombre de mi oferta "+ oferta.titulo)

        // PARA SACAR EL TIPO HACER UNA CONSULTA
        GlobalScope.launch(Dispatchers.Main)
        {
            async {
                val tipoOferta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .getTipoEmpleoByid(oferta.tipoempleo)
                if (tipoOferta.isSuccessful) {
                    rv_tiopTrabajo.text = tipoOferta.body()!!.descripcion
                }

            }
        }

        rv_btn_Editar.setOnClickListener(){

            val intent = Intent(context, FormEditEmpleo::class.java)
            intent.putExtra("OFERTA_SELECT", oferta)
            context.startActivity(intent)

        }

        rv_btn_Eliminar.setOnClickListener(){

            showDeleteAccountDialog(context, oferta)

        }



    }


    private fun showDeleteAccountDialog(context: Context, oferta: Oferta) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Eliminar Oferta de Trabajo ")
        builder.setMessage("¿Estás seguro de que deseas eliminar esta oferta ?")
        builder.setPositiveButton("Eliminar") { dialog: DialogInterface, which: Int ->

            GlobalScope.launch(Dispatchers.Main)
            {
                async {
                    val deleteOferta = Connection.getInstance().retrofit.create(APIservice::class.java)
                        .borrarMiEmpleo(oferta.id)
                    if (deleteOferta.isSuccessful) {
                        if(deleteOferta.body() == true){

                            val intent =  Intent(context, ListaMisOfertasCreadas::class.java)
                            context.startActivity(intent)
                        }
                    }
                }
            }

        }
        builder.setNegativeButton("Cancelar") { dialog: DialogInterface, which: Int ->


        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}









