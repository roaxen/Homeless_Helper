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
import com.example.dataStructure.datos.OfertraProvide
import com.example.hh_pantallas.reciclerVierw.OfertasRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Oferta
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.screen.Formularios.FormNewEmpleo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaOfertasEmpleo : AppCompatActivity()
{
    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    var listaOferta: MutableList<Oferta> = OfertraProvide.ofertas

    private lateinit var ofertasRvAdapter: OfertasRvAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ofertas_empleo)

        val rv_oferta = findViewById<RecyclerView>(R.id.RV_lista_ofertas_Ofertas)

        val buttonNewOferta = findViewById<FloatingActionButton>(R.id.lista_ofertas_new_oferta)

        buttonNewOferta.setOnClickListener {

            val intent = Intent(this, FormNewEmpleo::class.java)
            startActivity(intent)

        }


        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.lista_ofertas_include_barrasuperior)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Listado Ofertas")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {

            val intent = Intent(this, Menu_navegacion_principal::class.java)
            startActivity(intent)

        }

        rv_oferta.layoutManager = LinearLayoutManager(this)
        ofertasRvAdapter = OfertasRvAdapter(listaOferta)
        rv_oferta.adapter = ofertasRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {
         //   val ofertasTotales = Connection.getInstance().retrofit.create(APIservice::class.java).getAllEmpleos() // salen mis ofertas


            val ofertasTotales = Connection.getInstance().retrofit.create(APIservice::class.java).getAllEmpleosWhithMe(UsuarioLoginK.usuariokot!!) // las mias no salen

            if (ofertasTotales.isSuccessful) {
                val nouLugar = ofertasTotales.body() ?: emptyList()
                listaOferta.clear()
                listaOferta.addAll(nouLugar)
                ofertasRvAdapter.notifyDataSetChanged()
            }
        }
    }
}