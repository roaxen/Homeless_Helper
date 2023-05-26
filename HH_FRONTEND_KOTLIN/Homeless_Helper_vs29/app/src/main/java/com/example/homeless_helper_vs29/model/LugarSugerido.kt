package com.example.homeless_helper_vs29.model


data class LugarSugerido (

     val idLugarsugerido :String,
     val descripcion: String,
     val direccion: String,
     val email: String,
     val emailResponsable: String,
     val idTipolugar :String,
     val nombre: String,
     val telefono :String,
     val ubicacion: String,
     val valoracion :String,
)
data class LugarSugeridoCreate(

    val emailResponsable: String,
    val nombre: String,
    val direccion: String,
    val ubicacion: String,
    val idTipolugar :String,
    val telefono :String,
    val descripcion: String,
    val email: String,
    val valoracion :String,
)