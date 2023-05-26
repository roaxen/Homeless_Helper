package com.example.homeless_helper_vs29.screen

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homeless_helper_vs29.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.io.IOException

internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.maps_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Lugares Cerca Mio")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {
            val intent = Intent(this, Menu_navegacion_principal::class.java)
            startActivity(intent)
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.Maps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Convertir la dirección en coordenadas geográficas


        val address = "Pl. Mayor, 28012 Madrid"
       // val address = " C/ de Sardenya, 261, 08013 Barcelona"
        val geocoder = Geocoder(this)
        try {
            val results: List<Address> = geocoder.getFromLocationName(address, 1)?.toList() ?: emptyList()
            if (results.isNotEmpty()) {
                val location = results[0]
                val latLng = LatLng(location.latitude, location.longitude)

                // Agregar un marcador en la ubicación encontrada
                mMap.addMarker(MarkerOptions().position(latLng).title(address))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.moveCamera(CameraUpdateFactory.zoomTo(12f))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.isTrafficEnabled = false
    }
}


/*

// ANTES DE LA API DE GEOCODE
package com.example.homeless_helper_vs29.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homeless_helper_vs29.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menuBurgerBtn: ImageView
    private lateinit var TITULO_APP_HEADER: TextView

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.maps_include_header)
        TITULO_APP_HEADER = includeLayout.findViewById(R.id.header_TITULO_APP)
        TITULO_APP_HEADER.setText("Lugares Cerca Mio")
        menuBurgerBtn = includeLayout.findViewById(R.id.header_ic_burger)

        menuBurgerBtn.setOnClickListener {
            val intent = Intent(this, Menu_navegacion_principal::class.java)
            startActivity(intent)
        }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // EJEMPLO PARA QUE SALGA BARCELONA DESDE ARRIBA
        val POSICION_DEFECTO = LatLng(41.3874, 2.1686)
        mMap.addMarker(MarkerOptions().position(POSICION_DEFECTO).title("Marker in Barcelona")) // NOMBRE DE LA MARKA QUE APARECE
        mMap.moveCamera(CameraUpdateFactory.newLatLng(POSICION_DEFECTO))
        // mMap.mapType = GoogleMap.MAP_TYPE_HYBRID // TIPO SATELITE
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL // TIPO NORMAL

        mMap.moveCamera(CameraUpdateFactory.newLatLng(POSICION_DEFECTO))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12f)) //ZOOM PARA EL MAPA
        mMap.isTrafficEnabled = false // MOSTRAR EL TRAFICO


    }
}


 */