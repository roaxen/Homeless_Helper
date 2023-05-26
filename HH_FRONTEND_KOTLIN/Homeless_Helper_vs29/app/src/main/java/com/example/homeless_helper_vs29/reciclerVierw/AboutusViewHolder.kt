    package com.example.hh_pantallas.reciclerVierw

    import android.view.View
    import android.widget.ImageView
    import android.widget.TextView

    import androidx.recyclerview.widget.RecyclerView
    import com.example.homeless_helper_vs29.R
    import com.example.homeless_helper_vs29.model.Aboutus
    import com.example.homeless_helper_vs29.utils.ImageUtils


    class AboutusViewHolder (view: View): RecyclerView.ViewHolder(view) {


        // WIDGET DE LA APLICACION

        val rv_nombrecompleto = view.findViewById<TextView>(R.id.aboutus_nombre_apellidos);
        val rv_imagen = view.findViewById<ImageView>(R.id.aboutus_img);
        val rv_descripcion = view.findViewById<TextView>(R.id.aboutus_descripcion);


        fun printAbout(about: Aboutus){

            rv_nombrecompleto.text =  about.nombre + " "+ about.apellido1 + " " +about.apellido2
            rv_descripcion.text = about.descripcion.toString()

            val imageBytes = ImageUtils.stringToBitmap(about.imagen)
            if (imageBytes != null){
                rv_imagen.setImageBitmap(imageBytes)
            }else{
               // rv_imagen.setImageBitmap(imageBytes)  // PONER UNA IMAGEN POR DEFECTO
            }

            }
        }







