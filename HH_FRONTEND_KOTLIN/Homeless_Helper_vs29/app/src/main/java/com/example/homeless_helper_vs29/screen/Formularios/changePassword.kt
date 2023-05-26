package com.example.homeless_helper_vs29.screen.Formularios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_pantallas.reciclerVierw.LugaresRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.Encriptado
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioCreate
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Mi_Perfil
import com.example.homeless_helper_vs29.utils.Validator
import com.example.model.CredencialesChangePass
import com.example.model.CredencialesLogin
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class changePassword : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView


    // ELEMENTOS DE LA ACTIVITY
    private lateinit var switchShowPass: Switch
    private lateinit var inputActualPass: EditText
    private lateinit var inputNewPass: EditText
    private lateinit var inputNewRePass: EditText
    private lateinit var textErrorActualPass: TextView
    private lateinit var textErrorNewPass: TextView
    private lateinit var textErrorNewRePass: TextView
    private lateinit var changePassBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)


        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.change_password_header)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText("Cambiar Password")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)

        menubackBtn.setOnClickListener(){
            onBackPressed()
        }

        // ACTIVITY-- inicializacion
        switchShowPass = findViewById(R.id.change_pass_switch_showPass)
        inputActualPass = findViewById(R.id.change_pass_input_ActualPass)
        inputNewPass = findViewById(R.id.change_pass_input_NewPass)
        inputNewRePass = findViewById(R.id.change_pass_input_NewRePass)
        // WIDGET DE TEXT ERROR
        textErrorActualPass = findViewById(R.id.change_pass_TextError_ActualPass)
        textErrorNewPass = findViewById(R.id.change_pass_TextError_NewPass)
        textErrorNewRePass = findViewById(R.id.change_pass_TextError_NewRePass)

        changePassBtn = findViewById(R.id.change_pass_BTN_changePass)


        switchShowPass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Mostrar contraseña
                inputActualPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                inputNewPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                inputNewRePass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // Ocultar contraseña
                inputActualPass.transformationMethod = PasswordTransformationMethod.getInstance()
                inputNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                inputNewRePass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        changePassBtn.setOnClickListener(){

            // VALORES DE LOS IMPUTS
            val passActualValue =  Encriptado.encryptToMD5(inputActualPass.text.toString())
            val newPassValue = inputNewPass.text.toString()
            val newrRePassValue = inputNewRePass.text.toString()



            //COMPROBACION DE LOS POSIBLES ERRORES EN LOS CAMPOS DEL FORMULARIO ( CLASE VALIDATOR )

            val isActualPass = Validator.isPasswordUserLogin(passActualValue, textErrorActualPass, this )
            val isPasswordValid = Validator.isInputPassValid(newPassValue,textErrorNewPass, this )
            val isRePasswordValid = Validator.isInputRePassValid(newPassValue, newrRePassValue, textErrorNewRePass, this )

            if (isActualPass && isPasswordValid && isRePasswordValid)
            {

                // LLAMAR AL ENDPOINT PARA GUARDAR EL NUEVO PASS

                lifecycleScope.launch(Dispatchers.IO){

                    val UserChangePassOk = Connection.getInstance().retrofit.create(APIservice::class.java).changePassword(
                        CredencialesChangePass(UsuarioLoginK.usuariokot!!.email, UsuarioLoginK.usuariokot!!.clave,Encriptado.encryptToMD5(newPassValue)))

                    if (UserChangePassOk.isSuccessful){

                        if(UserChangePassOk.body()!!.email != null){

                            UsuarioLoginK.usuariokot = UserChangePassOk.body()  // SI LA RESPUESTA ES SUCCEFUL SI O SI VENDRA UN OBJETO construido

                            val intent =  Intent(this@changePassword, Mi_Perfil::class.java)

                            startActivity(intent)

                        }
                    }


                }

            }


        }

    }
}