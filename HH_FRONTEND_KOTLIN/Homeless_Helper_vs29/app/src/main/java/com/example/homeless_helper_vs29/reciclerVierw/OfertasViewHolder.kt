package com.example.hh_pantallas.reciclerVierw

import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.utils.ImageUtils
import com.example.homeless_helper_vs29.utils.UtilsTime
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class OfertasViewHolder (view: View): RecyclerView.ViewHolder(view)
{

    // IDEA, PONER UN BOTON QUE ENVIE UN MENSAJE AL USUARIO QUE TIENE EL EMAIL DEL RESPONSABLE


    // WIDGET DE LA APLICACION

    val rv_IMG_creador = view.findViewById<CircleImageView>(R.id.rv_layout_oferta_img_UserCreate);
    val rv_titulo = view.findViewById<TextView>(R.id.rv_layout_oferta_Titulo);
    val rv_fechacreacion = view.findViewById<TextView>(R.id.rv_layout_oferta_fecha_creacion);
    val rv_precio = view.findViewById<TextView>(R.id.rv_layout_oferta_Precio);
    val rv_tiopTrabajo = view.findViewById<TextView>(R.id.rv_layout_oferta_tipoEmpleo);
    val rv_descripcion = view.findViewById<TextView>(R.id.rv_layout_oferta_Descripcion);
    val rv_localidad = view.findViewById<TextView>(R.id.rv_layout_oferta_localidad);




    fun printOfertas(oferta: Oferta)
    {
        rv_titulo.text =  oferta.titulo
        rv_precio.text =  oferta.precio + " /h"
        rv_descripcion.text =  oferta.descripcion
        rv_fechacreacion.text = UtilsTime.convertirFechaAmericanaToEuropea(oferta.fecha)
        rv_localidad.text = oferta.localidad

        // poner la imagen del creador


       /*
        val imagen =ImageUtils.stringToBitmap(UsuarioLoginK.usuariokot!!.imagen)
        if(imagen != null){
            rv_IMG_creador.setImageBitmap(imagen)
        }
*/

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


        GlobalScope.launch(Dispatchers.Main)
        {
            async {

                val creadorOferta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .getUsuario(oferta.email)
                if (creadorOferta.isSuccessful)
                {
                    if(creadorOferta.body()!!.imagen != null){

                        ImageUtils.valorImageView(creadorOferta.body()!!.imagen,rv_IMG_creador)

                    }else{

                        rv_IMG_creador.setImageResource(R.drawable.no_foto)
                    }

                }
            }
        }


    }
}



/*



 */







