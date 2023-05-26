package com.example.homeless_helper_vs29.screen

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.homeless_helper_vs29.R

class activity_settings : AppCompatActivity() {

    private lateinit var switchDarkMode: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        switchDarkMode = findViewById(R.id.switchDarkMode)

        // Recuperar el estado actual del modo oscuro
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkModeEnabled = sharedPreferences.getBoolean("darkModeEnabled", false)
        switchDarkMode.isChecked = isDarkModeEnabled

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Guardar el estado del modo oscuro en las preferencias compartidas
            sharedPreferences.edit().putBoolean("darkModeEnabled", isChecked).apply()

            // Cambiar el modo de la aplicación según el estado del interruptor
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
