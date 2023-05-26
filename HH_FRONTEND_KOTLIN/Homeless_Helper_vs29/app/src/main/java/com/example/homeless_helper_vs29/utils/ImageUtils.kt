package com.example.homeless_helper_vs29.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageButton
import android.widget.ImageView
import com.example.homeless_helper_vs29.R
import java.io.ByteArrayOutputStream


class ImageUtils {
    companion object {
        /**
         * Metodo utilizado para cargar las imagenes de la base de datos en las vistas de la APP
         * Recibe la informacion base64 de la bbd (String) y si esta no esta vacia devuelve un Bitmap
         * @param String imgBase64
         * @return Bitmap
         */
        fun stringToBitmap(imagen: String, ): Bitmap? {
            val imageBytes = Base64.decode(imagen, Base64.DEFAULT)
            if (imageBytes != null) {
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                return bitmap
            }
            return null
        }


        fun valorImageView(imagenBase64: String, imagenView: ImageView) {

            val imagenPerfil = stringToBitmap(imagenBase64)

            if (imagenPerfil != null) {

                imagenView.setImageBitmap(imagenPerfil)
            } else {

                imagenView.setImageResource(R.drawable.no_foto)
            }


        }

        fun valorImageButton(imagenBase64: String, imagenButton: ImageButton) {

            val imagenPerfil = stringToBitmap(imagenBase64)

            if (imagenPerfil != null) {

                imagenButton.setImageBitmap(imagenPerfil)
            } else {

                imagenButton.setImageResource(R.drawable.no_foto)
            }


        }

        fun imagenDefault(context: Context): String {
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.no_foto)

            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, outputStream)
            val imageBytes = outputStream.toByteArray()

            val base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT)

            return base64Image
        }

// Mirar de hacer las comprobaciones dentro o fuera sobre si la imagene sta vacia o no


        /*
            // INSPIRACION
            val imageBytes = Base64.decode(UsuarioLoginK.usuariokot!!.imagen, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            inputImagen.setImageBitmap(bitmap)
            */




    }

}