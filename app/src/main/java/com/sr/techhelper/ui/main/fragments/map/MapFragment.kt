package com.sr.techhelper.ui.main.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostWithSender
import com.sr.techhelper.ui.main.PostsViewModel
import com.sr.techhelper.utils.ImageUtils


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.InfoWindowAdapter {
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private val viewModel: PostsViewModel by activityViewModels()

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
        viewModel.getAllPosts().observe(viewLifecycleOwner) { postsList ->
            if(postsList.isEmpty()) viewModel.invalidatePosts()
            postsList.forEach { post ->
                val latLng = LatLng(post.post.locationLat, post.post.locationLng)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(post.post.title)
                        .snippet(post.post.description)
                )?.tag = post // Set the postDTO as a tag
            }

            // Move the camera to show all markers
            if (postsList.isNotEmpty()) {
                val firstLocation = postsList.first()
                val firstLatLng = LatLng(firstLocation.post.locationLat, firstLocation.post.locationLng)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 2f))
            }
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
        val post = marker.tag as? PostWithSender ?: return null

        // Inflate the custom layout
        val infoWindowView = layoutInflater.inflate(R.layout.custom_info_window, null)

        // Set the content of the custom layout
        val titleTextView = infoWindowView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = infoWindowView.findViewById<TextView>(R.id.descriptionTextView)
        val imageView = infoWindowView.findViewById<ImageView>(R.id.imageView)
        val userNameTextView = infoWindowView.findViewById<TextView>(R.id.userIdTextView)



        titleTextView.text = post.post.title
        descriptionTextView.text = post.post.description
        userNameTextView.text = "By: ${post.sender.name}"

        post.post.image?.let {
            val bitmap = ImageUtils.decodeBase64ToImage(it)
            imageView.setImageBitmap(bitmap)
        }

        return infoWindowView
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to use the default window frame.
        return null
    }
}