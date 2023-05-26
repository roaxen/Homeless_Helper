package com.example.homeless_helper_vs29.screen

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dataStructure.datos.LugaresProvide
import com.example.hh_pantallas.reciclerVierw.LugaresRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.FavoritoPK
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.model.Lugares
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Mis_Lugares_Favoritos : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView


    var listaLugares: MutableList<Lugares> = LugaresProvide.lugares

    private lateinit var lugaresRvAdapter: LugaresRvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_lugares_favoritos)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.lugares_favoritos_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Mis Lugares Favoritos")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {

            val intent = Intent(this, Menu_navegacion_principal::class.java)
            intent.putExtra("pagina", "Mis_Lugares_Favoritos")
            startActivity(intent)
        }

        // WIDGET DE LA APLICACION
        val rv_lugares = findViewById<RecyclerView>(R.id.RV_Lugares_favoritos)
        rv_lugares.layoutManager = LinearLayoutManager(this)

        // BOTONES DE LA APLICACION



        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

// MOSTRAR LOS LUGARES FAVORITOS
        lifecycleScope.launch(Dispatchers.Main) {

            val usuarioActual = UsuarioLoginK.usuariokot

            if (usuarioActual != null) {
                val favoritoPK = FavoritoPK(usuarioActual)
                val response = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .getAllMiLugaresFavoritos(favoritoPK)

                if (response.isSuccessful) {
                    val nuevosLugares = response.body() ?: emptyList()
                    listaLugares.clear()
                    listaLugares.addAll(nuevosLugares)
                    lugaresRvAdapter.notifyDataSetChanged()
                } else {
                    Log.e(ContentValues.TAG, "Error al obtener los lugares favoritos")
                }
            } else {
                Log.e(ContentValues.TAG, "Usuario actual nulo")
            }
        }
    }
}