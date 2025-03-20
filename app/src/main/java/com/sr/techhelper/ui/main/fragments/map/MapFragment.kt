package com.sr.techhelper.ui.main.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.semantics.text
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sr.techhelper.data.posts.PostDTO
import com.sr.techhelper.ui.main.PostsViewModel
import com.sr.techhelper.ui.main.fragments.posts_list.PostsAdapter
import com.sr.techhelper.utils.decodeBase64ToImage


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

        viewModel.getAllPosts().observe(viewLifecycleOwner, {
            if(it.isEmpty()) viewModel.invalidatePosts()
            it.forEach { post ->
                var postDTO = post.toPostDto()
                val latLng = LatLng(postDTO.locationLat, postDTO.locationLng)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(postDTO.title)
                        .snippet(postDTO.description)
                )?.tag = postDTO // Set the postDTO as a tag
            }

            // Move the camera to show all markers
            if (it.isNotEmpty()) {
                val firstLocation = it.first()
                val firstLatLng = LatLng(firstLocation.locationLat, firstLocation.locationLng)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 2f))
            }
        })
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
        val post = marker.tag as? PostDTO ?: return null

        // Inflate the custom layout
        val infoWindowView = layoutInflater.inflate(R.layout.custom_info_window, null)

        // Set the content of the custom layout
        val titleTextView = infoWindowView.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = infoWindowView.findViewById<TextView>(R.id.descriptionTextView)
        val imageView = infoWindowView.findViewById<ImageView>(R.id.imageView)
        val userIdTextView = infoWindowView.findViewById<TextView>(R.id.userIdTextView)


        titleTextView.text = post.title
        descriptionTextView.text = post.description
        userIdTextView.text = "By: ${post.userId}"

        post.image?.let {
            val bitmap = decodeBase64ToImage(it)
            imageView.setImageBitmap(bitmap)
        }

        return infoWindowView
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to use the default window frame.
        return null
    }
}