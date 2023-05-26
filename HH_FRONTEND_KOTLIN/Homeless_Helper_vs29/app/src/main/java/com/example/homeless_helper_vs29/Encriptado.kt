package com.example.homeless_helper_vs29

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayInputStream
import java.security.MessageDigest
import java.nio.charset.StandardCharsets

class Encriptado {
    companion object {
        fun encryptToMD5(message: String): String {
            val md = MessageDigest.getInstance("MD5")
            val hash = md.digest(message.toByteArray(StandardCharsets.UTF_8))
            val hexString = StringBuilder()

            for (b in hash) {
                val hex = Integer.toHexString(0xff and b.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }

            return hexString.toString()
        }


        // llamar cuando recivimos la imagen de la base de datos

        fun bas64ToBitmap(userImagen:String):Bitmap?
        {
            if (userImagen != null)
            {
                val imageBytes = Base64.decode(userImagen, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                return bitmap;
            }
            return null;

        }


    }
}

/*
                    //Cargar Imagen desde Mysql de usuario por defecto. //Decodificar el imagen bytesArray a bitmap.
                    val imageBytes = Base64.decode(user?.image, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    UserImage.setImageBitmap(bitmap)
                    */
