package com.example.homeless_helper_vs29.utils

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.retrofit.APIservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SpinnerUtils {
    companion object
    {
// MIRAR SI SE ACABA IMPLEMENTANDO EN LAS ACTIVITY  --> formNewEmpleo

        /**Metodo para cargar los Spinner que tengan la Informacion de TipoEmpleo, usado en los formularios de Creacion
         * de empleo.
         * */
        fun cargarValoresSpinnerOferta(
            activity: AppCompatActivity,
            spinner: Spinner,
            empleo_id: MutableList<String>,
            empleo_descriptions: MutableList<String>
        ) {
            activity.lifecycleScope.launch(Dispatchers.Main) {
                val allTipoEmpleo =
                    Connection.getInstance().retrofit.create(APIservice::class.java).getAllTipoEmpleo()

                if (allTipoEmpleo.isSuccessful) {
                    val tipoEmpleoList = allTipoEmpleo.body() // Obtén la lista de tipo de empleo desde la respuesta

                    tipoEmpleoList?.let {
                        for (tipoEmpleo in it) {
                          //  val idTipolugar: Integer = tipoEmpleo.idTipolugar //Conversion del string a Integer
                            empleo_id.add(tipoEmpleo.id)
                            empleo_descriptions.add(tipoEmpleo.descripcion)
                        }
                    }

                    activity.runOnUiThread {
                        val adapter =
                            ArrayAdapter(activity, R.layout.simple_spinner_item, empleo_descriptions)
                        spinner.adapter = adapter
                    }
                }
            }
        }


        fun cargarValoresSpinnerTipoLugar(
            activity: AppCompatActivity,
            spinner: Spinner,
            tipoLugar_id: MutableList<String>,
            tipoLugar_descripcion: MutableList<String>) {

            activity.lifecycleScope.launch(Dispatchers.Main) {
                tipoLugar_id.add("")
                tipoLugar_descripcion.add("")
                val allTipoLugar =
                    Connection.getInstance().retrofit.create(APIservice::class.java).getAllTipoLugar()

                if (allTipoLugar.isSuccessful) {
                    val tipoLugarList = allTipoLugar.body() // Obtén la lista de tipo de empleo desde la respuesta


                    tipoLugarList?.let{


                        for (tipoLugar in it) {
                            tipoLugar_id.add(tipoLugar.idTipolugar)
                            tipoLugar_descripcion.add(tipoLugar.descripcion)

                        }
                    }

                    activity.runOnUiThread {
                        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, tipoLugar_descripcion)
                        spinner.adapter = adapter
                    }
                }

            }
        }

    }

 }


