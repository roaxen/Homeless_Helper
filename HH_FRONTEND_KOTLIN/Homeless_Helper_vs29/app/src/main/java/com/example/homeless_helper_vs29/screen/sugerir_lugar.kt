package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.screen.Formularios.FormSugerirLugar

class sugerir_lugar : AppCompatActivity() {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugerir_lugar)



        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.sugerir_lugar_include_header)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText("Formulario Lugar")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)


        menubackBtn.setOnClickListener {
            onBackPressed()
        }

        val btnsugerirLugar = findViewById<Button>(R.id.sugerir_lugar_BTN_sugerirLugar)

        btnsugerirLugar.setOnClickListener {

            val intent = Intent(this, FormSugerirLugar::class.java)
            startActivity(intent)
        }


    }
}