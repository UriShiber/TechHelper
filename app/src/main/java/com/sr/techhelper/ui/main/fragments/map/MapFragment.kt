package com.sr.techhelper.ui.main.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sr.techhelper.R

data class LocationData(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String
)

// Sample data
val sampleLocations = listOf(
    LocationData("Eiffel Tower", 48.8584, 2.2945, "Iconic wrought-iron lattice tower on the Champ de Mars in Paris."),
    LocationData("Louvre Museum", 48.8606, 2.3376, "World's largest art museum and a historic monument in Paris."),
    LocationData("Big Ben", 51.5007, -0.1246, "The nickname for the Great Bell of the striking clock at the north end of the Palace of Westminster in London."),
    LocationData("Colosseum", 41.8902, 12.4922, "Ancient amphitheater in the center of Rome, Italy."),
    LocationData("Statue of Liberty", 40.6892, -74.0445, "A colossal neoclassical sculpture on Liberty Island in New York Harbor.")
)

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMarkerClickListener(this)
        // Add markers for each sample location
        sampleLocations.forEach { location ->
            val latLng = LatLng(location.latitude, location.longitude)
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(location.name)
                    .snippet(location.description)
            )
        }

        // Move the camera to show all markers
        if (sampleLocations.isNotEmpty()) {
            val firstLocation = sampleLocations.first()
            val firstLatLng = LatLng(firstLocation.latitude, firstLocation.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 2f))
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        // Handle marker click here
        val location = sampleLocations.find { it.name == marker.title }
        location?.let {
            Toast.makeText(context, "Clicked on ${it.name}", Toast.LENGTH_SHORT).show()
        }
        // Return true to consume the event (prevent default behavior)
        return true
    }

    // Lifecycle methods for MapView
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}