package com.example.homeless_helper_vs29.utils

import java.text.SimpleDateFormat
import java.util.*


class UtilsTime {

    companion object {

        fun fechaActualDispositivo(): String {

            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            val dateStr = dateFormat.format(calendar.time).toString()

            return dateStr
        }


        fun fechaEscrita(inputDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd 'de' MMMM 'del' yyyy", Locale.getDefault())
           // val outputFormat = SimpleDateFormat("dd 'de' MMMM 'del' yyyy", Locale("es", "ES"))


            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        }


        // Para cuando el dispositivo esta en ingles y no cambiamos el idioma
        fun convertirFechaAmericanaToEuropea(inputDate: String): String
        {
            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fecha = formatoEntrada.parse(inputDate)
            val formatoSalida = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val fechaFormateada = formatoSalida.format(fecha!!)
            return fechaFormateada
        }

        // Impementarla para cuando usamos un date picker que no lo se poner en español mirar en el Modificar Mi perfil  y registro
        fun convertirFechaEuropeaToAmericana(): String
        {

            return ""
        }
    }
}