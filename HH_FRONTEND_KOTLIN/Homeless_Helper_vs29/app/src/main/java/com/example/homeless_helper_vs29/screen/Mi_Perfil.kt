package com.example.homeless_helper_vs29.screen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.Encriptado
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Formularios.Modificar_mi_perfil
import com.example.homeless_helper_vs29.screen.Formularios.changePassword
import com.example.homeless_helper_vs29.utils.ImageUtils
import com.example.homeless_helper_vs29.utils.UtilsTime
import com.example.model.CredencialesLogin
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class Mi_Perfil : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_perfil)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.mi_perfil_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Mi Información")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)
        menuBurgerBtn.setOnClickListener {

            val intent =  Intent(this, Menu_navegacion_principal::class.java)
            intent.putExtra("pagina", "Mi_Perfil")
            startActivity(intent)
        }


        // WIDGET DE LA APLICACION
        val inputEmail: TextView = findViewById(R.id.Mi_pefil_texto_email)
        val inputNombre: TextView = findViewById(R.id.Mi_pefil_texto_nombre)
        val inputFecha: TextView = findViewById(R.id.Mi_pefil_texto_fecha)
        val inputCiudad: TextView = findViewById(R.id.Mi_pefil_texto_ciudad)
        val inputImagen: CircleImageView = findViewById(R.id.mi_pefil_mi_foto)


        // BOTONES DE LA APLICACION
        val BTN_update_mi_info: Button = findViewById(R.id.Mi_pefil_BTN_modificarInfo)
        val BTN_update_password: Button = findViewById(R.id.Mi_pefil_BTN_changePassword)
        val BTN_delete_account: Button = findViewById(R.id.Mi_pefil_BTN_deleteAccount)



        if (UsuarioLoginK.usuariokot != null) {
            // RELLENO DE LA INFO DE LOS CAMPOS CON LA INFO DE USUARIOLOGIN

            inputEmail.setText(UsuarioLoginK.usuariokot!!.email)
            inputNombre.setText(UsuarioLoginK.usuariokot!!.nombre+" "+UsuarioLoginK.usuariokot!!.apellidos)
            inputCiudad.setText(UsuarioLoginK.usuariokot!!.ciudad)

            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fecha = formatoEntrada.parse(UsuarioLoginK.usuariokot!!.fechaNac)
            val formatoSalida = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val fechaFormateada = formatoSalida.format(fecha!!)

           val fechaEscrita= UtilsTime.fechaEscrita(UsuarioLoginK.usuariokot!!.fechaNac)

            inputFecha.setText(fechaEscrita) // ver que hacer con esto



            // DARLE VALOR A LA IMAGEN

            val imagenPerfil = ImageUtils.stringToBitmap(UsuarioLoginK.usuariokot!!.imagen)

            if (imagenPerfil != null){

                inputImagen.setImageBitmap(imagenPerfil)
            }else{

                inputImagen.setImageResource(R.drawable.no_foto)
            }

        }else{
            println("************************************  ALGO PASA CON EL USUARIO AQUI ")
        }



        BTN_update_mi_info.setOnClickListener() {

            val intent =  Intent(this, Modificar_mi_perfil::class.java)
            intent.putExtra("pagina", "Mi_Perfil")
            startActivity(intent)
        }

        BTN_update_password.setOnClickListener() {

            val intent =  Intent(this, changePassword::class.java)

            startActivity(intent)
        }

        BTN_delete_account.setOnClickListener() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Eliminar cuenta")
            builder.setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
            builder.setPositiveButton("Eliminar") { dialog: DialogInterface, which: Int ->

                lifecycleScope.launch(Dispatchers.Main) {

                    // ponerle que si es la cuenta de admin que no se puede borrar

                    val DeleteUserOK = Connection.getInstance().retrofit.create(APIservice::class.java)
                        .borrarCuenta(UsuarioLoginK.usuariokot!!.email)

                    if (DeleteUserOK.isSuccessful) {
                        if(DeleteUserOK.body() == true){
                            startActivity(Intent(this@Mi_Perfil, Login::class.java))

                        }

                    }


                }


                }
            builder.setNegativeButton("Cancelar") { dialog: DialogInterface, which: Int ->
                // Aquí puedes escribir el código para cancelar la eliminación de la cuenta
                // ...
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }


}