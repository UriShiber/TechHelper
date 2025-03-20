package com.sr.techhelper.ui.main.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.semantics.text
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sr.techhelper.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

data class LocationData(
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    val userId: String = "",
    val locationLng: Double = 0.0,
    val locationLat: Double = 0.0,
    val image: String? = null,
)

// Sample data
val sampleLocations = listOf(
    LocationData(
        id = "1",
        title = "Eiffel Tower",
        description = "Iconic wrought-iron lattice tower on the Champ de Mars in Paris.",
        userId = "user1",
        locationLat = 48.8584,
        locationLng = 2.2945,
        image = "https://picsum.photos/300/200"
    ),
    LocationData(
        id = "2",
        title = "Louvre Museum",
        description = "World's largest art museum and a historic monument in Paris.",
        userId = "user1",
        locationLat = 48.8606,
        locationLng = 2.3376,
        image = "https://picsum.photos/300/200"
    ),
    LocationData(
        id = "3",
        title = "Big Ben",
        description = "The nickname for the Great Bell of the striking clock at the north end of the Palace of Westminster in London.",
        userId = "user2",
        locationLat = 51.5007,
        locationLng = -0.1246,
        image = "https://picsum.photos/300/200"
    ),
    LocationData(
        id = "4",
        title = "Colosseum",
        description = "Ancient amphitheater in the center of Rome, Italy.",
        userId = "user2",
        locationLat = 41.8902,
        locationLng = 12.4922,
        image = "https://picsum.photos/300/200"
    ),
    LocationData(
        id = "5",
        title = "Statue of Liberty",
        description = "A colossal neoclassical sculpture on Liberty Island in New York Harbor.",
        userId = "user3",
        locationLat = 40.6892,
        locationLng = -74.0445,
        image = "https://picsum.photos/300/200"
    )
)

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.InfoWindowAdapter {

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
        googleMap.setInfoWindowAdapter(this)

        // Add markers for each sample location
        sampleLocations.forEach { location ->
            val latLng = LatLng(location.locationLat, location.locationLng)
            googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(location.title)
                    .snippet(location.description)
            )?.tag = location // Set the LocationData as a tag
        }

        // Move the camera to show all markers
        if (sampleLocations.isNotEmpty()) {
            val firstLocation = sampleLocations.first()
            val firstLatLng = LatLng(firstLocation.locationLat, firstLocation.locationLng)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 2f))
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        // Show the info window when the marker is clicked
        marker.showInfoWindow()
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

    override fun getInfoContents(marker: Marker): View? {
        // Get the LocationData from the marker's tag
        val location = marker.tag as? LocationData ?: return null

        // Inflate the custom layout
        val infoWindowView = layoutInflater.inflate(R.layout.custom_info_window, null)

        // Set the content of the custom layout
        val titleTextView = infoWindowView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = infoWindowView.findViewById<TextView>(R.id.descriptionTextView)
        val imageView = infoWindowView.findViewById<ImageView>(R.id.imageView)

        titleTextView.text = location.title
        descriptionTextView.text = location.description

        // Load the image using Glide
        Glide.with(this)
            .load(location.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)

        return infoWindowView
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to use the default window frame.
        return null
    }
}