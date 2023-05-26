package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hh_pantallas.reciclerVierw.LugaresRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.SearchRequest
import com.example.homeless_helper_vs29.model.TipoLugar
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.model.Lugares
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Home : AppCompatActivity()
{
    var listaLugares: MutableList<Lugares> = mutableListOf()

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView


    // ELEMENTOS DE LA ACTIVITY
    private lateinit var rv_lugares: RecyclerView
    private lateinit var lugaresRvAdapter: LugaresRvAdapter
    private lateinit var iconoAllBtn: ImageButton
    private lateinit var iconoComedorBtn: ImageButton
    private lateinit var iconoHospitalBtn: ImageButton
    private lateinit var iconoHostalBtn: ImageButton
    private lateinit var crearLugar: FloatingActionButton
    private lateinit var inputSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.home_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
            TITULO_APP_HEADER.setText("Homeless Helper")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)


        // ACTIVITY-- inicializacion (ICONOS PARA FILTRAR)
        iconoHostalBtn = findViewById(R.id.home_IMG_hostal)
        iconoComedorBtn = findViewById(R.id.home_IMG_Comedor)
        iconoHospitalBtn = findViewById(R.id.home_IMG_Hospital)
        iconoAllBtn = findViewById(R.id.home_IMG_All)
        crearLugar = findViewById(R.id.home_BTN_new_Lugar)
        inputSearch = findViewById(R.id.home_search_text)
        rv_lugares = findViewById(R.id.RV_home_Lugares)


        // ACCIONES DE LOS BOTONS
        setOnClickListeners()

        // CARGAR LA VISTA
        startViewHome()


// IMPLEMENTACION PARA QUE EL INPUTSEARCH BUSQUE AUTOMATICAMNETE
        inputSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Realizar búsqueda cada vez que el texto cambie
                val query = s.toString()
                viewSearch(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }


            override fun afterTextChanged(s: Editable?) {
            }
        })



    }

    private fun setOnClickListeners() {
        menuBurgerBtn.setOnClickListener {

            val intent = Intent(this, Menu_navegacion_principal::class.java)
            intent.putExtra("pagina", "home")
            startActivity(intent)
        }

        crearLugar.setOnClickListener {

            val intent = Intent(this, sugerir_lugar::class.java)
            startActivity(intent)
        }


        iconoAllBtn.setOnClickListener {
            startViewHome()
        }

        iconoComedorBtn.setOnClickListener {
            viewAllComedor()
        }

        iconoHospitalBtn.setOnClickListener {
            viewAllMedic()
        }

        iconoHostalBtn.setOnClickListener {
            viewAllAlbergue()
        }
    }

    private fun viewSearch(query: String) {
        rv_lugares.layoutManager = LinearLayoutManager(this)
        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

        val peticion = SearchRequest(query)
        lifecycleScope.launch(Dispatchers.Main) {
            val response = Connection.getInstance().retrofit.create(APIservice::class.java).searchLugar(peticion)

            if (response.isSuccessful) {
                val searchResults = response.body() ?: emptyList()
                listaLugares.clear()
                listaLugares.addAll(searchResults)
                lugaresRvAdapter.notifyDataSetChanged()
            }
        }
    }


    // Muestra la informacion de los ultimos lugares creados en la aplicacion
    private fun startViewHome() {

        rv_lugares.layoutManager = LinearLayoutManager(this)
        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {
            val lugareTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllLugares()

            if (lugareTotales.isSuccessful) {
                val nouLugar = lugareTotales.body() ?: emptyList()
                listaLugares.clear()
                listaLugares.addAll(nouLugar)
                lugaresRvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun viewAllComedor() {

        rv_lugares.layoutManager = LinearLayoutManager(this)
        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {
            val comedoresTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllLugarComedor(TipoLugar("1","Comedor"))

            if (comedoresTotales.isSuccessful) {
                val nouLugar = comedoresTotales.body() ?: emptyList()
                comedoresTotales.body()
                listaLugares.clear()
                listaLugares.addAll(nouLugar)
                lugaresRvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun viewAllAlbergue() {

        rv_lugares.layoutManager = LinearLayoutManager(this)
        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {
            val albergueTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllLugarComedor(TipoLugar("2","Albergues"))

            if (albergueTotales.isSuccessful) {
                val nouLugar = albergueTotales.body() ?: emptyList()
                listaLugares.clear()
                listaLugares.addAll(nouLugar)
                lugaresRvAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun viewAllMedic() {

        rv_lugares.layoutManager = LinearLayoutManager(this)
        lugaresRvAdapter = LugaresRvAdapter(listaLugares)
        rv_lugares.adapter = lugaresRvAdapter

        lifecycleScope.launch(Dispatchers.Main) {
            val capTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllLugarCAP(TipoLugar("5","CAP (Centro Atencion Primaria)"))

            if (capTotales.isSuccessful) {
                val nouLugar = capTotales.body() ?: emptyList()
                listaLugares.clear()
                listaLugares.addAll(nouLugar)
                lugaresRvAdapter.notifyDataSetChanged()
            }
            val hospitalesTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAllLugarCAP(TipoLugar("3","Hospital"))

            if (hospitalesTotales.isSuccessful) {
                val nouLugar = hospitalesTotales.body() ?: emptyList()

                listaLugares.addAll(nouLugar)
                lugaresRvAdapter.notifyDataSetChanged()
            }
        }
    }


    override fun onBackPressed() {
    }
}
