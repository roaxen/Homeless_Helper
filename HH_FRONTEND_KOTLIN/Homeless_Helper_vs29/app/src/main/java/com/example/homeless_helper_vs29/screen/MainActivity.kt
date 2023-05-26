package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.homeless_helper_vs29.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // CODIGO PARA QUE ESPERE 2s Y NAVEGUE A LA SIGUIUENTE PANTALLA
        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }, 2000)


    }
}