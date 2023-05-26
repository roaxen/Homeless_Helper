package com.example.model

import java.io.Serializable

data class Lugares(val idLugar:Int,
                       val descripcion: String,
                       val direccion: String,
                       val email: String,
                       val nombre: String,
                       val telefono:Int,
                       val emailResponsable: String,
                       val idTipolugar:String,
                       val ubicacion:String,
                       val imagen : String,
                       val valoracion:Int): Serializable