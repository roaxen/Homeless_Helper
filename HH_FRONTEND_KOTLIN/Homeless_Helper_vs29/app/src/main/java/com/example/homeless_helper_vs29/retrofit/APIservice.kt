package com.example.homeless_helper_vs29.retrofit


import com.example.homeless_helper_vs29.model.*
import com.example.model.CredencialesChangePass
import com.example.model.CredencialesLogin
import com.example.model.Lugares
import retrofit2.Response
import retrofit2.http.*


interface APIservice {

    // TABLA ABOUT
    @GET("aboutuses")
    suspend  fun getAbout(): Response<List<Aboutus>>

// ---------------------------------------------------------------------------------------------

// Usando Usuariokot.kt
    /**
     * API GET Usuario, obtiene todos los usuarios de la bbdd
     * @return List(Usuarios)
     * */
    @GET
    suspend  fun getAllUsuario(@Url url:String): Response<List<Usuariokot>>  // todos los usuario

    /**
     * API POST Usuario, inserta un usuario sin imagen en la bbdd
     * Siempre retorna un usuario, si la insercion es incorrecta retorna un User.email = null
     * @return Usuario
     * */
    @POST("signin")
    suspend fun insertUsuario(@Body email: UsuarioCreate):  Response<Usuariokot> // crear un usuario

    @GET("profile/{email}")
    suspend fun getUsuario(@Path("email") email:String): Response<Usuariokot>

    @POST("login")
    suspend fun checkLogin(@Body  credenciales: CredencialesLogin): Response<Usuariokot> // hacer login

    @PUT("profile")
    suspend fun updateUser(@Body  usuario: UsuarioUpdate): Response<Usuariokot>

    @PUT("changeUserPwd")
    suspend fun changePassword(@Body  credenciales: CredencialesChangePass): Response<Usuariokot>

    @DELETE("user/{email}")
    suspend fun borrarCuenta(@Path("email") email: String ): Response<Boolean>


    // ---------------------------------------------------------------------------------------------
// TABLA LUGARES_SUGERIDOS

    @POST("addLugarsugerido")
    suspend  fun addLugarSujerido(@Body  lugarSugerido: LugarSugeridoCreate): Response<LugarSugerido>

    // ---------------------------------------------------------------------------------------------

    // TABLA LUGARES

    @GET("lugares")
    suspend  fun getAllLugares(): Response<List<Lugares>>

    @DELETE("lugar/{id}")
    suspend fun getAllLugaresById(@Path("id") id: String): Response<Lugares> // 404 qu ees etooooooh

    @POST("getLugarByTipo")
    suspend  fun getAllLugarComedor(@Body tipoLugar:TipoLugar ): Response<List<Lugares>>

    @POST("getLugarByTipo")
    suspend  fun getAllLugarCAP(@Body tipoLugar:TipoLugar ): Response<List<Lugares>>

    @POST("searchLugares")
    suspend  fun searchLugar(@Body busqueda:SearchRequest ): Response<List<Lugares>>


    // ---------------------------------------------------------------------------------------------
// TABLA TIPO_LUGAR

    @GET("tipoLugar/{idTipolugar}")
    suspend  fun getTipoLugarByid(@Path("idTipolugar") id: String): Response<TipoLugar>


    @GET ("tiposLugar")
    suspend  fun getAllTipoLugar(): Response<List<TipoLugar>>  // todos los usuario


    // ---------------------------------------------------------------------------------------------

    // TABLA Favorito
    @POST("newFavorito")
    suspend  fun insertFavorito(@Body  favorito: Favorito): Response<Favorito>

    @POST("lugaresFavoritos")
    suspend  fun getAllMiLugaresFavoritos(@Body  favoritopk: FavoritoPK): Response<List<Lugares>>

    @DELETE("deleteFavorito/{id}/{email}")
    suspend fun borrarFavorito(@Path("id") id: Int, @Path("email") email: String): Response<Boolean>

    @POST("favorito")
    suspend  fun existFavoritoLugar(@Body  favorito: Favorito): Response<Boolean>
// ---------------------------------------------------------------------------------------------


    // TABLA OFERTA EMPLEO

    @GET("empleos")
    suspend  fun getAllEmpleos(): Response<List<Oferta>>

    @POST("otrosEmpleos")
    suspend  fun getAllEmpleosWhithMe(@Body usuario: Usuariokot): Response<List<Oferta>>

    @POST("addEmpleo")
    suspend  fun createEmpleo(@Body  empleoCreate: OfertaCreate): Response<OfertaCreate>

    @PUT("updateEmpleo")
    suspend  fun modificarEmpleo(@Body  empleoCreate: OfertaUpdate): Response<Oferta>

    @POST("myEmpleos")
    suspend  fun getAllMiOfertas(@Body user: Usuariokot ): Response<List<Oferta>>

    @DELETE("eliminarEmpleo/{id}")
    suspend fun borrarMiEmpleo(@Path("id") id: String): Response<Boolean>   // DECIRLE A PABLO QUE LO ACABE DE IMPLEMENTAR

// ---------------------------------------------------------------------------------------------
    // TABLA TIPO_EMPLEO

    @GET ("tiposEmpleo")
    suspend  fun getAllTipoEmpleo(): Response<List<TipoEmpleo>>  // todos los usuario

    @GET("tipoEmpleo/{idTipoempleo}")
    suspend  fun getTipoEmpleoByid(@Path("idTipoempleo") id: String): Response<TipoEmpleo>

// ---------------------------------------------------------------------------------------------
}