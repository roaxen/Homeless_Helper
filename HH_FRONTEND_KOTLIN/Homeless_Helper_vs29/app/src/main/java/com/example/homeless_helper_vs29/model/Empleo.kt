package com.example.homeless_helper_vs29.model

import java.io.Serializable

data class Oferta ( // usao para lo demas

     var id: String,
     var email: String,
     var fecha: String, // calculado la fercha del momento
     var titulo: String,
     var precio: String,
     var tipoempleo: String,  // si da error hay que pasarlo a Integer
     var localidad: String,
     var descripcion: String,
    var imagen: String,
): Serializable


class OfertaCreate ( // Uso para el crear

    var email: String,
    var fecha: String, // calculado la fercha del momento
    var titulo: String,
    var precio: String,
    var tipoempleo: String,  // si da error hay que pasarlo a Integer
    var localidad: String,
    var descripcion: String,
    //var imagen: ByteArray,

)

class OfertaUpdate ( // Uso para el crear

    var id: String,
    var email: String,
    var fecha: String, // calculado la fercha del momento
    var titulo: String,
    var precio: String,
    var tipoempleo: String,  // si da error hay que pasarlo a Integer
    var localidad: String,
    var descripcion: String,
    //var imagen: ByteArray,

)