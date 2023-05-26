package com.example.homeless_helper_vs29.screen.Formularios

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.Encriptado
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioCreate
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.model.Usuariokot
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Home
import com.example.homeless_helper_vs29.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class Register_User : AppCompatActivity()
{
    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView

    // INSTANCIAMOS LA CONEXION A LA BASE DE DATOS
    val conexion = Connection.getInstance();

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.register_include_app)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText("Register Account ")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)

        menubackBtn.setOnClickListener(){
            onBackPressed()
        }



        // WIDGET DE LA APLICACION
        val inputEmail = findViewById<EditText>(R.id.emailFormRegistro)
        val inputPass = findViewById<EditText>(R.id.passFormRegistro)
        val inputRePass = findViewById<EditText>(R.id.RepitpassFormRegistro)
        val inputName = findViewById<EditText>(R.id.nombreFormRegistro)
        val inputSurname = findViewById<EditText>(R.id.apellidosFormRegistro)
        val inputFecha = findViewById<DatePicker>(R.id.modificar_mi_pefil_datepicker)
        val inputCiudad = findViewById<EditText>(R.id.ciudadFormRegistro)

        // WIDGET DE TEXT ERROR
        val emailTxtError = findViewById<TextView>(R.id.register_error_email)
        val passTxtError = findViewById<TextView>(R.id.register_error_pass)
        val repassTxtError = findViewById<TextView>(R.id.register_error_repass)
        val nameTxtError = findViewById<TextView>(R.id.register_error_nombre)
        val surnameTxtError = findViewById<TextView>(R.id.register_error_apellidos)
        val dateTxtError = findViewById<TextView>(R.id.register_error_edad)
        val cityTxtError = findViewById<TextView>(R.id.register_error_ciudad)

        // BOTONES DE LA APLICACION
        val btn_create_user: Button = findViewById(R.id.register_btn_registrar)
        val showPass = findViewById<CheckBox>(R.id.register_Check_eye);



        showPass.setOnClickListener()  // Arreglar lo del estilo del hint
        {
            if (showPass.isChecked) {
                // mostrar la contraseña
                showPass.setButtonDrawable(R.drawable.eye_close_24px)
                inputPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                inputRePass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                /*
                // metodo manual menos eficiente, porque modifica el estilo de los inputs
                inputPass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                inputRePass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                */

            } else {
                // Ocultar la contraseña
                showPass.setButtonDrawable(R.drawable.eye_close_24px)
                inputPass.transformationMethod = PasswordTransformationMethod.getInstance()
                inputRePass.transformationMethod = PasswordTransformationMethod.getInstance()

                /*
                inputPass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                inputRePass.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWOR
                */
            }
        }


        btn_create_user.setOnClickListener()
        {

            // VALORES DE LOS IMPUTS
            val emailValue = inputEmail.text.toString()
            val passValue = inputPass.text.toString()
            val rePassValue = inputRePass.text.toString()
            val nameValue = inputName.text.toString()
            val surnameValue = inputSurname.text.toString()
            val cityValue = inputCiudad.text.toString()

            // CALCULO DE LA FECHA
            val year = inputFecha.year
            val month = inputFecha.month
            val dayOfMonth = inputFecha.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            //COMPROBACION DE LOS POSIBLES ERRORES EN LOS CAMPOS DEL FORMULARIO ( CLASE VALIDATOR )
            val isEmailValid = Validator.isInputEmailValid(emailValue,emailTxtError, this )
            val isPasswordValid = Validator.isInputPassValid(passValue,passTxtError, this )
            val isRePasswordValid = Validator.isInputRePassValid(rePassValue, passValue, repassTxtError, this )
            val isNombreValid = Validator.isThisInputFull(nameValue, nameTxtError, this)
            val isApellidoValid = Validator.isThisInputFull(surnameValue, surnameTxtError, this)
            val isFechaValid = Validator.isOver18(calendar, dateTxtError, this );



            if (isEmailValid && isPasswordValid && isRePasswordValid && isNombreValid && isApellidoValid && isFechaValid)
            {
                    lifecycleScope.launch(Dispatchers.IO)
                    {
                        //  val registerOK = conexion.retrofit.create(APIservice::class.java).insertUsuario( Usuario(email, pass, nombre,apellido,fecha,ciudad))
                        val passEncr = Encriptado.encryptToMD5(passValue)

                        // CONVERTIR LA FECHA A STRING PARA GUARDARLA
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                        val dateStr = dateFormat.format(calendar.time).toString()

                        //Usuariokot.kt
                        val registerOK = conexion.retrofit.create(APIservice::class.java).insertUsuario( UsuarioCreate(emailValue, passEncr, nameValue,surnameValue,cityValue, dateStr))

                        if (registerOK.isSuccessful)
                        {
                            val body = registerOK.body()
                            if (body?.email == (emailValue))
                            {
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

                                withContext(Dispatchers.Main)
                                {
                                    Toast.makeText(this@Register_User,"Usuario creado ", Toast.LENGTH_SHORT ).show()
                                }
                                // NAVEGACION
                                startActivity(Intent(this@Register_User, Home::class.java))

                            }
                            else
                            {
                                withContext(Dispatchers.Main)
                                {
                                    Toast.makeText(this@Register_User, "Error al crear Usuario",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }

                        }else
                        {
                            withContext(Dispatchers.Main)
                            {
                                Toast.makeText(this@Register_User, "Error en la conexion, cierra la app",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    }
            }
        }
    }
}
