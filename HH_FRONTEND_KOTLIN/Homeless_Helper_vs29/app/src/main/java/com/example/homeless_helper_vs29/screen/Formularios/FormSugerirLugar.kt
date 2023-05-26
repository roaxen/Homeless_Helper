package com.example.homeless_helper_vs29.screen.Formularios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.LugarSugeridoCreate
import com.example.homeless_helper_vs29.model.OfertaCreate
import com.example.homeless_helper_vs29.model.TipoLugar
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Home
import com.example.homeless_helper_vs29.screen.ListaOfertasEmpleo
import com.example.homeless_helper_vs29.screen.Menu_navegacion_principal
import com.example.homeless_helper_vs29.utils.UtilsTime
import com.example.homeless_helper_vs29.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormSugerirLugar : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView

    // ELEMENTOS DEL ACTIVITY
    private lateinit var nombre: EditText
    private lateinit var direccion: EditText
    private lateinit var localidad: EditText
    private lateinit var spinnerLugar: Spinner
    private lateinit var telefono: EditText
    private lateinit var masInfo: EditText
    private lateinit var btnCrearLugar: Button

    // WIDGET DE TEXT ERROR
    private lateinit var nombreTxtError: TextView
    private lateinit var direccionTxtError: TextView
    private lateinit var localidadTxtError: TextView
    private lateinit var spinnerLugarTxtError: TextView

    // ELEMENTOS DEL SPINNER
    private var selectedIdValue: String = ""
    private var selectedDescripcionValue: String = ""
    val tipoLugar_id = mutableListOf<String>()
    val tipoLugar_descriptions = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_sugerir_lugar)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.form_sugerir_lugar_include_header)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText("Formulario Lugar")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)

        // ACTIVITY-- inicializacion
        nombre = findViewById(R.id.form_sugerir_lugar_nombre)
        direccion = findViewById(R.id.form_sugerir_lugar_direccion)
        localidad = findViewById(R.id.form_sugerir_lugar_ubicacion)
        telefono = findViewById(R.id.form_sugerir_lugar_telefono)
        masInfo = findViewById(R.id.form_sugerir_lugar_masInfo)
        btnCrearLugar = findViewById(R.id.form_sugerir_lugar_BTN_crear_sugerencia)

        // SPINNER -- inicializacion
        spinnerLugar = findViewById(R.id.form_sugerir_lugar_spinnerLugar)
        cargarValoresSpinnerTipoLugar(spinnerLugar, tipoLugar_id, tipoLugar_descriptions)

        // TEXT_ERROR -- inicializacion
        nombreTxtError = findViewById(R.id.form_sugerir_lugar_TextError_nombre)
        direccionTxtError = findViewById(R.id.form_sugerir_lugar_TextError_Direccion)
        localidadTxtError = findViewById(R.id.form_sugerir_lugar_TextError_Localidad)
        spinnerLugarTxtError = findViewById(R.id.form_sugerir_lugar_TextError_TipoLugar)


        // BUTTON-- setOnClickListener
        menubackBtn.setOnClickListener(){
            onBackPressed()

        }


        // METODO PARA CREAR EL OBJETO LUGAR Sugerido
        btnCrearLugar.setOnClickListener(){

            val nombreValue = nombre.text.toString()
            val direccionValue = direccion.text.toString()
            val localidadValue = localidad.text.toString()
            val telefonoValue = telefono.text.toString()
            val masInfoValue = masInfo.text.toString()

            //COMPROBACION DE LOS POSIBLES ERRORES EN LOS CAMPOS DEL FORMULARIO ( CLASE VALIDATOR )
            val isNombreValid = Validator.isThisInputFull(nombreValue,nombreTxtError, this )
            val isDireccionValid = Validator.isThisInputFull(direccionValue,direccionTxtError, this )
            val isLocalidadValid = Validator.isThisInputFull(localidadValue, localidadTxtError, this)
            val isSpinnerValid = Validator.isThisInputFull(selectedIdValue, spinnerLugarTxtError, this)

            if (isNombreValid && isDireccionValid && isLocalidadValid && isSpinnerValid)
            {

                lifecycleScope.launch(Dispatchers.Main) {

                    val lugarSugerido = LugarSugeridoCreate(
                        UsuarioLoginK.usuariokot!!.email,
                        nombreValue,
                        direccionValue,
                        localidadValue,
                        selectedIdValue,
                        telefonoValue,
                        masInfoValue,
                        "",
                        "")
                    val lugarSugeridoOk =
                        Connection.getInstance().retrofit.create(APIservice::class.java).addLugarSujerido(lugarSugerido )

                  if (lugarSugeridoOk.isSuccessful){
                      if(lugarSugeridoOk.body()!!.email != null){

                          val intent = Intent(this@FormSugerirLugar, Home::class.java)
                          intent.putExtra("pagina", "home")
                          startActivity(intent)



                      }
                  }


                }
            }





        }

        // METODO PARA EXTRAER LO QUE A SELECCIONADO DEL SPINNER
        spinnerLugar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerLugar.selectedItem.toString()

                selectedIdValue = tipoLugar_id[position]



                // boton.setText(selectedIdValue) // PRUEBA PARA VER EL VALOR ED LA ID   ********************************** BORRAR LA LINEA

            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se seleccionó nada
            }
        }

    }
    fun cargarValoresSpinnerTipoLugar(spinner: Spinner, tipoLugar_id: MutableList<String>, tipoLugar_descripcion: MutableList<String>) {
        lifecycleScope.launch(Dispatchers.Main) {
            tipoLugar_id.add("")
            tipoLugar_descripcion.add("")
            val allTipoLugar =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllTipoLugar()

            if (allTipoLugar.isSuccessful) {
                val tipoLugarList = allTipoLugar.body() // Obtén la lista de tipo de empleo desde la respuesta


                tipoLugarList?.let{

                    // DE  POR DEFECTO YA SON NULL PERO LOS REASIGNAMOS DE NUEVO PARA EVITAR PROBLEMAS
                    selectedIdValue = it[0].idTipolugar
                    selectedDescripcionValue = it[0].descripcion

                    for (tipoLugar in it) {
                        // val idTipolugar: Integer = tipoEmpleo.idTipolugar //Conversion del string a Integer
                        //LO DEJAMOS EN STRING ASI EL PRIMER VALOR LO PONEMOS A ""
                        println("LOS VALORES SON EN EL BUCLE "+ tipoLugar.idTipolugar + "   " + tipoLugar.descripcion+"\n")
                        tipoLugar_id.add(tipoLugar.idTipolugar)
                        tipoLugar_descripcion.add(tipoLugar.descripcion)

                    }
                }

                runOnUiThread {
                    val adapter = ArrayAdapter(this@FormSugerirLugar, android.R.layout.simple_spinner_item, tipoLugar_descripcion)
                    spinner.adapter = adapter
                }
            }

        }
    }
}