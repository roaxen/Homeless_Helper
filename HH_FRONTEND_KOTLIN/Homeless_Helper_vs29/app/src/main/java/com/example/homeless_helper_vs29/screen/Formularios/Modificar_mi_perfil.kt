package com.example.homeless_helper_vs29.screen.Formularios

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.graphics.BitmapFactory


import android.util.Base64
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.model.UsuarioUpdate
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Mi_Perfil
import com.example.homeless_helper_vs29.utils.ImageUtils
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

class Modificar_mi_perfil : AppCompatActivity() {

    private lateinit var inputImagen: CircleImageView
    private  var imageBytes: ByteArray = byteArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_mi_perfil)

        // BOTONES DE LA APLICACION
        val BTN_update: Button = findViewById(R.id.modificar_pefil_BTN_guardarInfo)

        val BTN_Back: ImageButton = findViewById(R.id.modificar_pefil_BTN_back)
        BTN_Back.setOnClickListener() {
            val intent = Intent(this, Mi_Perfil::class.java)
            intent.putExtra("pagina", "modificar_mi_perfil")
            startActivity(intent)

        }

        // VARIABLES
        val inputNombre: TextView = findViewById(R.id.modificar_pefil_texto_nombre)
        val inputApellidos: TextView = findViewById(R.id.modificar_pefil_texto_apellido)
        val inputCiudad: TextView = findViewById(R.id.modificar_pefil_texto_ciudad)
        val inputFecha = findViewById<DatePicker>(R.id.modificar_pefil_datepicker)
         inputImagen = findViewById(R.id.modificar_pefil_mi_foto)


        // DARLE VALOR A LOS CAMPOS
        inputNombre.setText(UsuarioLoginK.usuariokot!!.nombre)
        inputApellidos.setText(UsuarioLoginK.usuariokot!!.apellidos)
        inputCiudad.setText(UsuarioLoginK.usuariokot!!.ciudad)
        ImageUtils.valorImageView(UsuarioLoginK.usuariokot!!.imagen,inputImagen )

        // DARLE VALOR A LA FECHA
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(UsuarioLoginK.usuariokot!!.fechaNac)
        val calendar = Calendar.getInstance()
        calendar.time = date
        inputFecha.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null)




    // ASSEGURARSE DE QUE SI EL USUARIO NO SUBE UNA IMAGEN SE GUARDE LA QUE TENIA POR DEFECTO
        if(UsuarioLoginK.usuariokot!!.imagen != null){
            val base64Image = UsuarioLoginK.usuariokot!!.imagen
            val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
            imageBytes = decodedBytes
        }



        // CODIGO PARA SUBIR UNA IMAGEN
        inputImagen.setOnClickListener()
        {
            val intent =  Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 10)
        }


        BTN_update.setOnClickListener() {

          //  UsuarioLogin.getInstance().UsuarioLoginCambioImagen(imageBytes);

            lifecycleScope.launch(Dispatchers.Main)
            {

                val nombre = inputNombre.text.toString()
                val apellidos = inputApellidos.text.toString()
                val ciudad = inputCiudad.text.toString()

                val year = inputFecha.year
                val month = inputFecha.month
                val dayOfMonth = inputFecha.dayOfMonth
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val dateStr = dateFormat.format(calendar.time).toString()



                val usuarioUpdate = UsuarioUpdate(UsuarioLoginK.usuariokot!!.email,
                    UsuarioLoginK.usuariokot!!.clave,
                    nombre,
                    apellidos,
                    ciudad,
                    dateStr,
                    imageBytes)

                val registerOK = Connection.getInstance().retrofit.create(APIservice::class.java).updateUser(usuarioUpdate)

                if (registerOK.isSuccessful)
                {
                    if (registerOK?.body()?.email != null){
                        UsuarioLoginK.usuariokot = registerOK.body()  // SI LA RESPUESTA ES SUCCEFUL SI O SI VENDRA UN OBJETO construido

                        val intent =  Intent(this@Modificar_mi_perfil, Mi_Perfil::class.java)
                        //intent.putExtra("pagina", "Mi_Perfil")
                        startActivity(intent)

                    }
                    else {
                         withContext(Dispatchers.Main)
                         {
                             Toast.makeText(this@Modificar_mi_perfil, "ERROR AL UPDATEAR EL USER",
                                 Toast.LENGTH_SHORT).show()
                         }

                    }
                }
            }

/*
            val intent =  Intent(this, Mi_Perfil::class.java)
            //intent.putExtra("pagina", "Mi_Perfil")
            startActivity(intent)
*/


        }


    }
    //Guardar imagen seleccionado en imageView.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            inputImagen.setImageURI(selectedImage)  // Asignamos la imagen al elemento de la interfaz de usuario


             println("DENTRO DE CONVERTIR LA IMAGEN ")
            // Convertir la imagen seleccionada a un ByteArray y comprimirla
            val inputStream: InputStream? = selectedImage?.let { contentResolver.openInputStream(it) }
            val byteArrayOutputStream = ByteArrayOutputStream()
            if (inputStream != null) {
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } != -1) {
                    byteArrayOutputStream.write(buffer, 0, length)
                }
                inputStream.close()

                // Comprimir la imagen en formato JPEG
                val quality = 10 // Calidad de compresión (0-100)
                val scaledBitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size())
                byteArrayOutputStream.reset()
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
            }

            // Obtener los bytes de la imagen comprimidal
            imageBytes = byteArrayOutputStream.toByteArray()
        }
    }


}

// FUNCIONA PARA IMAGENES PEQUEÑAS
/*
//Guardar imagen seleccionado en imageView.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            inputImagen.setImageURI(selectedImage)  //  tiene un objeto imahen asignamos al la imagen el valor

            //conventir el imagen seleccionado a un bytearray.
            val inputStream: InputStream? = selectedImage?.let { contentResolver.openInputStream(it) }
            val byteArrayOutputStream = ByteArrayOutputStream()
            if (inputStream != null) {
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } != -1) {
                    byteArrayOutputStream.write(buffer, 0, length)
                }
                inputStream.close()
            }
            imageBytes = byteArrayOutputStream.toByteArray()
        }
    }

 */



// *****************************************

    /*


      // CODIGO PARA SUBIR UNA IMAGEN
        inputImagen = findViewById(R.id.Mi_pefil_mi_foto)

        inputImagen.setOnClickListener()
        {
            val intent =  Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 10)
        }

    }
    //Guardar imagen seleccionado en imageView.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            inputImagen.setImageURI(selectedImage)  //  tiene un objeto imahen asignamos al la imagen el valor

            //conventir el imagen seleccionado a un bytearray.
            val inputStream: InputStream? = selectedImage?.let { contentResolver.openInputStream(it) }
            val byteArrayOutputStream = ByteArrayOutputStream()
            if (inputStream != null) {
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } != -1) {
                    byteArrayOutputStream.write(buffer, 0, length)
                }
                inputStream.close()
            }
            imageBytes = byteArrayOutputStream.toByteArray()
        }
    }

     */