package com.example.homeless_helper_vs29.screen

import android.graphics.Point
import android.graphics.PointF
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.homeless_helper_vs29.Connection
import com.example.homeless_helper_vs29.R
import com.example.homeless_helper_vs29.retrofit.APIservice
import com.example.homeless_helper_vs29.utils.ImageUtils
import com.example.model.Lugares
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class Info_Lugar_Select : AppCompatActivity(), OnMapReadyCallback {
    // ELEMENTOS DEL INCLUDE
    private lateinit var includeLayout: View
    private lateinit var menubackBtn: ImageView
    private lateinit var header_TITULO: TextView


    // ELEMENTOS DE LA ACTIVITY
    private lateinit var imagenLugar: ImageView
    private lateinit var nombre: TextView
    private lateinit var tipoLugar: TextView
    private lateinit var direccion: TextView
    private lateinit var descripcion: TextView
    private lateinit var telefono: TextView
    private lateinit var emailContacto: TextView
    private lateinit var MapSelectLugar: GoogleMap
    private lateinit var LugarSelecionado: Lugares
    private lateinit var btnZoomIn: ImageButton
    private lateinit var btnZoomOut: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_lugar_select)

        // INCLUDE-- inicializacion
        includeLayout = findViewById(R.id.info_lugar_include_header)
        header_TITULO = includeLayout.findViewById(R.id.header_TITULO)
        header_TITULO.setText(" Info Lugar")
        menubackBtn = includeLayout.findViewById(R.id.header_ic_back)

        menubackBtn.setOnClickListener(){
            onBackPressed()
        }


        // ACTIVITY-- inicializacion
         imagenLugar = findViewById(R.id.rv_layout_info_lugar_Imagen)
         nombre = findViewById(R.id.rv_layout_info_lugar_Nombre)
         tipoLugar = findViewById(R.id.rv_layout_info_lugar_TipoLugar)
         direccion = findViewById(R.id.rv_layout_info_lugar_direccion)
         descripcion = findViewById(R.id.rv_layout_info_lugar_Descripcion)
         telefono = findViewById(R.id.rv_layout_info_lugar_Telefono)
         emailContacto = findViewById(R.id.rv_layout_info_lugar_email)
        // imagenMAPS = findViewById(R.id.rv_layout_info_lugar_ImagenMAPS)



        // MAPS - variables necesarias
        LugarSelecionado = intent.getSerializableExtra("LUGAR_SELECT") as Lugares
        btnZoomIn = findViewById(R.id.rv_layout_info_lugar_BTN_ZoomIn)
        btnZoomOut = findViewById(R.id.rv_layout_info_lugar_BTN_ZoomOut)
        btnZoomIn.setOnClickListener {
            MapSelectLugar.animateCamera(CameraUpdateFactory.zoomIn())
        }

        btnZoomOut.setOnClickListener {
            MapSelectLugar.animateCamera(CameraUpdateFactory.zoomOut())
        }



        // Inicializar la vista
        if (LugarSelecionado.imagen != null){
            var imagen = ImageUtils.stringToBitmap(LugarSelecionado.imagen)

            if(imagen != null ){
                imagenLugar.setImageBitmap(imagen)
            }
        }

        nombre.setText(LugarSelecionado.nombre)
        direccion.setText(LugarSelecionado.direccion)
        descripcion.setText(LugarSelecionado.descripcion)
        telefono.setText(LugarSelecionado.telefono.toString())
        emailContacto.setText(LugarSelecionado.email)
       // imagenMAPS.setImageResource(R.drawable.ejem_google_maps)

        // PARA el tipo de lugar
        GlobalScope.launch(Dispatchers.Main)
        {
            async {
                val tipoOferta = Connection.getInstance().retrofit.create(APIservice::class.java)
                    .getTipoLugarByid(LugarSelecionado.idTipolugar)
                if (tipoOferta.isSuccessful) {

                    tipoLugar.text =tipoOferta.body()!!.descripcion

                }

            }
        }






        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.rv_layout_info_lugar_Maps) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onMapReady(googleMap: GoogleMap) {
        MapSelectLugar = googleMap

        // Convertir la dirección en coordenadas geográficas
        val geocoder = Geocoder(this)
        try {
            val results: List<Address> = geocoder.getFromLocationName(LugarSelecionado.direccion, 1)?.toList() ?: emptyList()
            if (results.isNotEmpty()) {
                val location = results[0]
                val latLng = LatLng(location.latitude, location.longitude)

                // Agregar un marcador en la ubicación encontrada
                val markerOptions = MarkerOptions().position(latLng).title(LugarSelecionado.nombre)
                MapSelectLugar.addMarker(markerOptions)

                // Mover la cámara al marcador y ajustar el zoom
                MapSelectLugar.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        MapSelectLugar.mapType = GoogleMap.MAP_TYPE_NORMAL
        MapSelectLugar.isTrafficEnabled = false
    }

}