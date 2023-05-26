package com.example.homeless_helper_vs29.screen.Formularios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.OfertaCreate
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Home
import com.example.homeless_helper_vs29.utils.UtilsTime
import com.example.homeless_helper_vs29.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormNewEmpleo : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView

    // ELEMENTOS DEL SPINNER
    private var selectedIdValue: String = ""
    private var selectedDescripcionValue: String = ""
    val empleo_id = mutableListOf<String>()
    val empleo_descriptions = mutableListOf<String>()

    // ELEMENTOS DE LA ACTIVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_new_empleo)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.from_new_empleo_header)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText("Nueva Oferta")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)

        menubackBtn.setOnClickListener(){
            onBackPressed()
        }


        // WIDGET DE LA APLICACION
        var spinner = findViewById<Spinner>(R.id.form_new_empleo_spinner_tipotrabajo)
        cargarValoresSpinnerOferta(spinner, empleo_id, empleo_descriptions)

        var titulo = findViewById<EditText>(R.id.form_new_empleo_input_titulo)
        var precio = findViewById<EditText>(R.id.form_new_empleo_input_precio)
        var localidad = findViewById<EditText>(R.id.form_new_empleo_input_localidad)
        var descripcion = findViewById<EditText>(R.id.from_new_empleo_input_descrip_multiline)

        // WIDGET DE TEXT ERROR
        val spinnerTxtError = findViewById<TextView>(R.id.from_new_empleo_TextError_Spinner)
        val tituloTxtError = findViewById<TextView>(R.id.from_new_empleo_TextError_Titulo)
        val precioTxtError = findViewById<TextView>(R.id.from_new_empleo_TextError_precio)
        val localidadTxtError = findViewById<TextView>(R.id.from_new_empleo_TextError_Localidad)
        val descripcionTxtError = findViewById<TextView>(R.id.from_new_empleo_TextError_Descripcion)

        // BOTONES DE LA APLICACION
        var boton = findViewById<Button>(R.id.form_new_empleo_BTN_crearOferta)



         // METODO PARA EXTRAER LO QUE A SELECCIONADO DEL SPINNER
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = spinner.selectedItem.toString()

                selectedIdValue = empleo_id[position]
                selectedDescripcionValue = empleo_descriptions[position]


               // boton.setText(selectedIdValue) // PRUEBA PARA VER EL VALOR ED LA ID   ********************************** BORRAR LA LINEA

            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se seleccionó nada
            }
        }


        boton.setOnClickListener(){

            // VALORES DE LOS IMPUTS

            val tituloValue = titulo.text.toString()
            val precioValue = precio.text.toString()
            val localidadValue = localidad.text.toString()
            val descripcionValue = descripcion.text.toString()

            //COMPROBACION DE LOS POSIBLES ERRORES EN LOS CAMPOS DEL FORMULARIO ( CLASE VALIDATOR )
            val isTituloValid = Validator.isThisInputFull(tituloValue,tituloTxtError, this )
            val isPrecioValid = Validator.isInputNumberValid(precioValue,precioTxtError, this )
            val isLocalidadValid = Validator.isThisInputFull(localidadValue, localidadTxtError, this)
            val isDescripcionValid = Validator.isThisInputFull(descripcionValue, descripcionTxtError, this)
            val isSpinnerValid = Validator.isThisInputFull(selectedIdValue, spinnerTxtError, this)

            if (isTituloValid && isPrecioValid && isLocalidadValid && isDescripcionValid && isSpinnerValid)
            {

                lifecycleScope.launch(Dispatchers.Main) {
                    val ofertanueva = OfertaCreate(
                        UsuarioLoginK.usuariokot!!.email,
                        UtilsTime.fechaActualDispositivo(),
                        tituloValue.toString(),
                        precioValue.toString(),
                        selectedIdValue,
                        localidadValue.toString(),
                        descripcionValue.toString()
                    )

                   val ofertaOK = Connection.getInstance().retrofit.create(APIservice::class.java).createEmpleo(ofertanueva)

                    if (ofertaOK.isSuccessful) {
                        if(ofertaOK.body()?.email != null){

                            onBackPressed()
                        }
                    }
                }
            }

        }

    }


    fun cargarValoresSpinnerOferta(spinner: Spinner, empleo_id: MutableList<String>, empleo_descriptions: MutableList<String>) {
      lifecycleScope.launch(Dispatchers.Main) {
          empleo_id.add("")
          empleo_descriptions.add("")
            val allTipoEmpleo =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllTipoEmpleo()

            if (allTipoEmpleo.isSuccessful) {
                val tipoEmpleoList = allTipoEmpleo.body() // Obtén la lista de tipo de empleo desde la respuesta


                tipoEmpleoList?.let{

                    // DE  POR DEFECTO YA SON NULL PERO LOS REASIGNAMOS DE NUEVO PARA EVITAR PROBLEMAS
                    selectedIdValue = it[0].id
                    selectedDescripcionValue = it[0].descripcion

                    for (tipoEmpleo in it) {
                       // val idTipolugar: Integer = tipoEmpleo.idTipolugar //Conversion del string a Integer
                        //LO DEJAMOS EN STRING ASI EL PRIMER VALOR LO PONEMOS A ""
                        println("LOS VALORES SON EN EL BUCLE "+ tipoEmpleo.id + "   " + tipoEmpleo.descripcion+"\n")
                        empleo_id.add(tipoEmpleo.id)
                        empleo_descriptions.add(tipoEmpleo.descripcion)

                    }
                }

                runOnUiThread {
                    val adapter = ArrayAdapter(this@FormNewEmpleo, android.R.layout.simple_spinner_item, empleo_descriptions)
                    spinner.adapter = adapter
                }
            }

      }
    }





}