package com.example.model




data class CredencialesLogin(

    val email: String,
    val clave: String,

)

data class CredencialesChangePass (

    val email: String,
    val clave: String,
    val new_clave: String
    )

