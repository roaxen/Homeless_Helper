package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_pantallas.reciclerVierw.OfertasRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.reciclerVierw.MisOfertasRvAdapter
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Formularios.FormNewEmpleo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ListaMisOfertasCreadas : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    private lateinit var rv_oferta :RecyclerView
    private lateinit var buttonNewOferta :FloatingActionButton


    var listaOferta: MutableList<Oferta> = mutableListOf()
    private lateinit var ofertasRvAdapter: MisOfertasRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_mis_ofertas_creadas)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.lista_mis_ofertas_creadas_include_barrasuperior)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Mis Ofertas Creadas")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {

            val intent = Intent(this, Menu_navegacion_principal::class.java)
            startActivity(intent)

        }




        rv_oferta = findViewById(R.id.RV_lista_mis_ofertas_creadas_Ofertas)
        buttonNewOferta = findViewById(R.id.lista_mis_ofertas_creadas_new_oferta)

        buttonNewOferta.setOnClickListener {
            val intent = Intent(this, FormNewEmpleo::class.java)
            startActivity(intent)
        }

        rv_oferta.layoutManager = LinearLayoutManager(this)
        ofertasRvAdapter = MisOfertasRvAdapter(listaOferta)
        rv_oferta.adapter = ofertasRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {

            val ofertasTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllMiOfertas(UsuarioLoginK.usuariokot!!)

            if (ofertasTotales.isSuccessful) {
                val nouLugar = ofertasTotales.body() ?: emptyList()
                listaOferta.clear()
                listaOferta.addAll(nouLugar)


                ofertasRvAdapter.notifyDataSetChanged()
                //actualizarVista()

            }
        }


    }

    /*

    // MIRAR COMO IMPLEMENTAR UNA VISTA DISTINTA SI LA PETICION RETORNA NINGUN ELEMENTO

    private fun actualizarVista() {
        if (listaOferta.isEmpty()) {
            rv_oferta.visibility = View.GONE
            val containerLayout = findViewById<View>(R.id.lista_mis_ofertas_creadas_container)

            val vistaVacia: View = layoutInflater.inflate(R.layout.layout_lista_vacia, containerLayout, false)
            containerLayout.addView(vistaVacia)
        } else {
            rv_oferta.visibility = View.VISIBLE
            ofertasRvAdapter.notifyDataSetChanged()
        }
    }
    */


}