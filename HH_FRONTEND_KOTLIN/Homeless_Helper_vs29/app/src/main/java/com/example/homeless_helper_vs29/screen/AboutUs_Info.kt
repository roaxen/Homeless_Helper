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
import com.example.hh_pantallas.reciclerVierw.AboutusRvAdapter
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.Aboutus
import com.example.homeless_helper_vs29.retrofit.APIservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AboutUs_Info : AppCompatActivity() {

    var listaAbout: MutableList<Aboutus> = mutableListOf()

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    // ELEMENTOS DE LA ACTIVITY
    private lateinit var aboutRvAdapter: AboutusRvAdapter
    private lateinit var rv_about: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us_info)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.aboutUs_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("About Us ")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {
            val intent = Intent(this, Menu_navegacion_principal::class.java)
            intent.putExtra("pagina", "AboutUs_Info")
            startActivity(intent)

        }

        // ACTIVITY-- inicializacion ( CARGAR LA VISTA )
        rv_about = findViewById(R.id.aboutus_rv_about)
        rv_about.layoutManager = LinearLayoutManager(this)
        aboutRvAdapter = AboutusRvAdapter(listaAbout)
        rv_about.adapter = aboutRvAdapter


        lifecycleScope.launch(Dispatchers.Main)
        {
            val aboutTotales =
                Connection.getInstance().retrofit.create(APIservice::class.java).getAbout()

            if (aboutTotales.isSuccessful) {
                val nouAbout = aboutTotales.body() ?: emptyList()
                listaAbout.clear()
                listaAbout.addAll(nouAbout)
                aboutRvAdapter.notifyDataSetChanged()
            }
        }
    }
}