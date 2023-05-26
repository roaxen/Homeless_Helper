package com.example.homeless_helper_vs29.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.model.UsuarioLoginK
import com.example.homeless_helper_vs29.utils.ImageUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView



class Menu_navegacion_principal : AppCompatActivity() {

    private lateinit var navigationView: NavigationView
    private lateinit var headerView: View
    private lateinit var imagenUsuario: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_navegacion_principal)


        // OBTENER LOS ELEMENTOS QUE FORMAN EL ACTIVITY
        navigationView = findViewById(R.id.navigation_view)
        headerView = navigationView.getHeaderView(0)
        imagenUsuario = headerView.findViewById(R.id.menu_lateral_img_perfil)

        val buttonHambur = findViewById<FloatingActionButton>(R.id.menu_burger_lateral)
        // DARLE VALOR A LA IMAGEN

        ImageUtils.valorImageView(UsuarioLoginK.usuariokot!!.imagen,imagenUsuario )



            buttonHambur.setOnClickListener {
                // código a ejecutar cuando se haga clic en la imagen
                //  startActivity(Intent(this, Home::class.java))
                onBackPressed()
            }

        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        // listado de opciones del mene + implementacion
        navigationView.setNavigationItemSelectedListener {
                menuItem ->
            when (menuItem.itemId) {
                R.id.nav_Home_Lugares -> {
                    val intent =  Intent(this, Home::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_Favoritos_Lugares -> {
                    val intent =  Intent(this, Mis_Lugares_Favoritos::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_Lista_Trabajo -> {
                      val intent =  Intent(this, ListaOfertasEmpleo::class.java)
                      startActivity(intent)
                    true
                }

                R.id.nav_Trabajos_Creados -> {
                    val intent =  Intent(this, ListaMisOfertasCreadas::class.java)
                    startActivity(intent)

                    true
                }

                R.id.nav_Mensajes -> {


                    true
                }

                R.id.nav_Mi_Perfil -> {
                    val intent =  Intent(this, Mi_Perfil::class.java)
                    startActivity(intent);
                    true
                }

                R.id.nav_AboutUs -> {
                    val intent =  Intent(this, AboutUs_Info::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_GoogleMaps -> {
                    val intent =  Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_sugerirLugar -> {
                    val intent =  Intent(this, sugerir_lugar::class.java)
                    startActivity(intent)
                    true
                }
/*
                R.id.nav_settings -> {
                    val intent =  Intent(this, activity_settings::class.java)
                    startActivity(intent)
                    true
                }
 */
                R.id.nav_logOut -> {

                    UsuarioLoginK.deleteInstance()
                    val intent =  Intent(this, Login::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

    }

}