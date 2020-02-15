package com.example.stroke.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stroke.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
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

        // Add a marker in Sydney and move the camera
        val romania = LatLng(47.255899, 26.623105)

        val iasi = LatLng(47.1611086,27.6071117)
        mMap.addMarker(MarkerOptions().position(iasi).title("Spitalul Clinic de Urgenta “Prof.Dr. N.Oblu” Iasi"))
        val bacau = LatLng(46.5562709,26.9104817)
        mMap.addMarker(MarkerOptions().position(bacau).title("Spitalul Judetean de Urgenta Bacau"))
        val piatraNeamt = LatLng(46.9285851,26.3733885)
        mMap.addMarker(MarkerOptions().position(piatraNeamt).title("Spitalul Judetean de Urgenta Piatra Neamt"))
        val vaslui = LatLng(46.6554212,27.7357071)
        mMap.addMarker(MarkerOptions().position(vaslui).title("Spitalul Judetean de Urgenta Vaslui"))
        val botosani = LatLng(47.7408261,26.6612495)
        mMap.addMarker(MarkerOptions().position(botosani).title("Spitalul Judetean de Urgenta Mavromati Botosani"))
        val suceava = LatLng(47.6389845,26.2401385)
        mMap.addMarker(MarkerOptions().position(suceava).title("Spitalul Judetean de Urgenta Sfantul Ioan cel Nou Suceava"))
        val focsani = LatLng(45.7048695,27.1952786)
        mMap.addMarker(MarkerOptions().position(focsani).title("Spitalul Judetean de Urgenta Sfantul Pantelimon Focsani\n"))
        val barlad = LatLng(46.2351738,27.671231)
        mMap.addMarker(MarkerOptions().position(barlad).title("Spitalul Judetean de Urgenta Elena Beldiman Barlad"))

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(romania, 7.5f))
    }
}
