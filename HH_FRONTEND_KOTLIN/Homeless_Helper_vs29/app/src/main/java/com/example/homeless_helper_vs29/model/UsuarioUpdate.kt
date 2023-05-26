package com.example.homeless_helper_vs29.model

data class UsuarioUpdate(

    var email: String,
    var clave: String,
    var nombre:String,
    var apellidos: String,
    var ciudad: String,
    var fechaNac: String,
    var imagen: ByteArray

)

data class Usuariokot(

    var email: String,
    var clave: String,
    var nombre:String,
    var apellidos: String,
    var ciudad: String,
    var fechaNac: String,
    var imagen: String
)

data class UsuarioCreate(

    var email: String,
    var clave: String,
    var nombre:String,
    var apellidos: String,
    var ciudad: String,
    var fechaNac: String



)