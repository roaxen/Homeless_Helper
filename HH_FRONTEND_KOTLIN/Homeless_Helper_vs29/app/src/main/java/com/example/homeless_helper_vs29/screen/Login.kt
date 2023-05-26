package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.Encriptado
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.model.Usuariokot
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Formularios.Register_User
import com.example.model.CredencialesLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // WIDGET DE LA APLICACION
        val inputEmail = findViewById<EditText>(R.id.INP_ingrese_email)
        val inputPass = findViewById<EditText>(R.id.INP_ingrese_password)


        // WIDGET DE TEXT ERROR
        val emailTxtError = findViewById<TextView>(R.id.login_textError_email)
        val passTxtError = findViewById<TextView>(R.id.login_textError_pass)


        // BOTONES DE LA APLICACION
        val btnLogin: Button = findViewById(R.id.BTN_login)
        val btnRegister: Button = findViewById(R.id.BTN_register)

        val logo: ImageView = findViewById(R.id.login_IMG_Logo)
       // logo.circularImage()



        // NAVEGACION DE LOS BOTONES
        btnRegister.setOnClickListener {

            runOnUiThread {
                startActivity(Intent(this, Register_User::class.java));
            }
        }




        btnLogin.setOnClickListener{

            val inputEmail = inputEmail.text.toString()
            val inputPass = inputPass.text.toString()


            // IMPEMETAR CUANDO DEJEMOS DE ENTRAR COMO ADMIN
            /*
            //COMPROBACION CAMPOS VACIOS + UI
            val inputEmailOk = Validator.isInputEmailValid(inputEmail, emailTxtError, this)
            val inputPassOk = Validator.isInputPassValid(inputPass, passTxtError, this)


            if ( inputEmailOk && inputPassOk )
            {
            */// IMPEMETAR CUANDO DEJEMOS DE ENTRAR COMO ADMIN
            if ( !inputEmail.isEmpty() && !inputPass.isEmpty() ) {

                lifecycleScope.launch(Dispatchers.Main) {
                    val usuarioOK = Connection.getInstance().retrofit.create(APIservice::class.java)
                        .checkLogin(CredencialesLogin(inputEmail, Encriptado.encryptToMD5(inputPass)))

                    if (usuarioOK.isSuccessful) {

                        val body = usuarioOK.body()
                        if (body?.email != null && body.email == inputEmail) {

                            val imagen = body?.imagen ?: ""

                            val usuarioLogeado = Usuariokot(
                                body.email,
                                body.clave,
                                body.nombre,
                                body.apellidos,
                                body.ciudad,
                                body.fechaNac,
                                imagen
                            )
                            UsuarioLoginK.usuariokot = usuarioLogeado
                            println("Valor de UsuarioLoginK.usuariokot -> " + UsuarioLoginK.usuariokot + "\n")
                                startActivity(Intent(this@Login, Home::class.java))
                        }else
                        {
                            runOnUiThread {
                                Toast.makeText(
                                    this@Login,
                                    "El usuario o password incorrecto",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    }
                    else {
                        withContext(Dispatchers.Main)
                        {
                            Toast.makeText(
                                this@Login,
                                "Error en la conexion, cierra la app",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }// courutina
            }else{
                runOnUiThread {
                    Toast.makeText(
                        this@Login,
                        "Usuario o contraseña incorrectos ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onBackPressed() {
    }
}