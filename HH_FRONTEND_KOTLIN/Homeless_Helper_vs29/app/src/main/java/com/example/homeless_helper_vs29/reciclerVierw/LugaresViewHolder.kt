    package com.example.hh_pantallas.reciclerVierw
    import android.content.Context
    import android.content.Intent
    import kotlinx.coroutines.async

    import android.view.View
    import android.widget.ImageButton
    import android.widget.TextView

    import android.widget.ToggleButton
    import androidx.recyclerview.widget.RecyclerView

    import com.example.homeless_helper_vs29.Connection
    import com.example.homeless_helper_vs29.R
    import com.example.homeless_helper_vs29.model.Favorito
    import com.example.homeless_helper_vs29.model.UsuarioLoginK
    import com.example.homeless_helper_vs29.retrofit.APIservice
    import com.example.homeless_helper_vs29.screen.Info_Lugar_Select
    import com.example.homeless_helper_vs29.utils.ImageUtils
    import com.example.model.Lugares
    import kotlinx.coroutines.CompletableDeferred
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch



    class LugaresViewHolder (view: View): RecyclerView.ViewHolder(view) {


        val rv_nombre = view.findViewById<TextView>(R.id.home_rv_tv_nombre);
        val rv_imagen = view.findViewById<ImageButton>(R.id.home_rv_tv_image);
        val rv_star = view.findViewById<ToggleButton>(R.id.home_rv_toggle_star)



        fun printLugares(lugares: Lugares, context: Context) {

            rv_nombre.text = lugares.nombre


            if (lugares.imagen != null){
                var imagen = ImageUtils.stringToBitmap(lugares.imagen)

                if(imagen != null ){
                    rv_imagen.setImageBitmap(imagen)
                }
            }



            rv_imagen.setOnClickListener(){

                val intent = Intent(context, Info_Lugar_Select::class.java)
                intent.putExtra("LUGAR_SELECT", lugares)
                context.startActivity(intent)


            }

            // ************************ COMPROBACION DE QUE LAS ESTRELLAS ESTAN MARCADAS ********************

    // CODIGO PARA PONER TODOS LOS LUGARES QUE SEAN FAVORITOS EN ESTRELA ON

            var existefavorito = false;

            GlobalScope.launch(Dispatchers.Main)
            {
                val deferredResult = async {
                    if (UsuarioLoginK.usuariokot != null) {
                        isFavoriteLugar(Favorito(UsuarioLoginK.usuariokot!!.email, lugares.idLugar))
                    } else {
                        false
                    }
                }
                existefavorito = deferredResult.await()

                if (existefavorito == true){
                    rv_star.setButtonDrawable(android.R.drawable.btn_star_big_on)
                    rv_star.isChecked = true
                }else{
                    rv_star.setButtonDrawable(android.R.drawable.btn_star_big_off)
                    rv_star.isChecked = false
                }
            }

            rv_star.setOnClickListener()
            {

                var objetoEndpoint = Favorito(UsuarioLoginK.usuariokot!!.email, lugares.idLugar)

                if (rv_star.isChecked == true)
                {
                    GlobalScope.launch(Dispatchers.IO)
                    {
                        val deferredResult = async {
                            insertarFavorito(objetoEndpoint)
                        }
                    }
                    rv_star.setButtonDrawable(android.R.drawable.btn_star_big_on)
                }

                if (rv_star.isChecked == false)
                {
                    GlobalScope.launch(Dispatchers.IO)
                    {
                        val deferredResult = async {
                            borrarFavorito(objetoEndpoint)
                        }
                    }
                    rv_star.setButtonDrawable(android.R.drawable.btn_star_big_off)
                }
            }
        }




        suspend fun insertarFavorito(objetoEndpoint: Favorito): Boolean {
            val resultado = CompletableDeferred<Boolean>()

            GlobalScope.launch(Dispatchers.IO) {

                val respuesta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .insertFavorito(objetoEndpoint)

                if (respuesta.isSuccessful)
                {
                    if(respuesta.body()!= null)
                    {
                        resultado.complete(true)
                        println("Insertado exitosamente.")
                    }
                    else
                    {
                        resultado.complete(false)
                        println("Error al insertar.")
                    }
                }
                else
                {
                    resultado.complete(false)
                    println("Error con la base de Datos.")
                }
            }

            return resultado.await()
        }


        suspend fun borrarFavorito(objetoEndpoint: Favorito): Boolean {
            val resultado = CompletableDeferred<Boolean>()

            GlobalScope.launch(Dispatchers.IO)
            {

                val respuesta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .borrarFavorito(objetoEndpoint.id.idLugar, objetoEndpoint.id.email )

                if (respuesta.isSuccessful)
                {
                    if(respuesta.body()!= null)
                    {
                        resultado.complete(true)
                        println("Borrado exitosamente.")
                    }
                    else
                    {
                        resultado.complete(false)
                        println("Error al borrar.")
                    }
                }
                else
                {
                    resultado.complete(false)
                    println("Error con la base de Datos.")
                }

            }

            return resultado.await()
        }

        suspend fun isFavoriteLugar(objetoEndpoint: Favorito): Boolean
        {
            val resultado = CompletableDeferred<Boolean>()
            GlobalScope.launch(Dispatchers.Main)
            {
                val respuesta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .existFavoritoLugar(objetoEndpoint )

                if (respuesta.isSuccessful)
                {
                    if(respuesta.body() == true)
                    { // comprobacion del resultado
                        resultado.complete(true)
                    }
                    else if (respuesta.body() == false)
                    {
                        resultado.complete(false)
                    }
                }
                else
                {
                    resultado.complete(false)
                    println("ERROR CON LA BASE DE DATOS ")
                }
            }
            return resultado.await()
        }
    }










