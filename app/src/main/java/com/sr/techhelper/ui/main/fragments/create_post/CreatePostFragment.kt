package com.sr.techhelper.ui.main.fragments.create_post

import GeminiApiClient
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.semantics.error
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.BuildConfig
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.ui.main.PostsViewModel
import com.sr.techhelper.utils.ImageUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.text.isBlank
import kotlin.text.isNullOrEmpty

class CreatePostFragment : Fragment() {
    private val viewModel: PostsViewModel by activityViewModels()
    private var userUid: String = FirebaseAuth.getInstance().currentUser!!.uid
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var geminiApiClient: GeminiApiClient

    private lateinit var imageView: ImageView
    private var base64Image: String? = null
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0
    private var generatedTags = listOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val apiKey = BuildConfig.API_KEY
        geminiApiClient = GeminiApiClient(apiKey)

        val saveButton: Button = view.findViewById(R.id.save_button)
        val cancelButton: Button = view.findViewById(R.id.cancel_button)
        val generateTagsButton: Button = view.findViewById(R.id.generate_tags_button)
        val titleEditText = view.findViewById<EditText>(R.id.add_post_title_edit_text)
        val descriptionEditText = view.findViewById<EditText>(R.id.add_post_description_edit_text)
        val generatedTagsTextView = view.findViewById<TextView>(R.id.generated_tags_text_view)
        imageView = view.findViewById<ImageView>(R.id.create_post_image)

        imageView.setOnClickListener {
            pickImageFromGallery()
        }

        generateTagsButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                generatedTags = geminiApiClient.generateTags(title, description)
                withContext(Dispatchers.Main) {
                    generatedTagsTextView.text = generatedTags.joinToString(", ")
                }
            }
        }

        cancelButton.setOnClickListener {
            val action = CreatePostFragmentDirections.actionCreatePostFragmentToPostListFragment()
            Navigation.findNavController(it).navigate(action)
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNav.selectedItemId = R.id.home  // Set Home as selected
        }

        saveButton.setOnClickListener {
            getCurrentLocation() {
                if (viewModel.isPostValid(
                        titleEditText.text.toString(),
                        base64Image,
                        descriptionEditText.text.toString()
                    )
                ) {
                    val newPost = PostModel(
                        userId = userUid,
                        title = titleEditText.text.toString(),
                        description = descriptionEditText.text.toString(),
                        locationLat = currentLatitude,
                        locationLng = currentLongitude,
                        image = base64Image,
                        tags = generatedTags
                    )
                    viewModel.addPost(newPost)
                    val action =
                        CreatePostFragmentDirections.actionCreatePostFragmentToPostListFragment()
                    Navigation.findNavController(it).navigate(action)
                    val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottomNav.selectedItemId = R.id.home  // Set Home as selected
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



    private fun getCurrentLocation(onFinish: () -> Unit = {}) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions
            requestLocationPermission()
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    currentLatitude = location.latitude
                    currentLongitude = location.longitude
                    Log.d("CreatePostFragment", "Latitude: $currentLatitude, Longitude: $currentLongitude")
                } else {
                    Log.e("CreatePostFragment", "Location is null")
                }
                onFinish()
            }
            .addOnFailureListener { e ->
                Log.e("CreatePostFragment", "Error getting location: ${e.message}")
            }
    }

    private fun requestLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                getCurrentLocation()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                getCurrentLocation()
            }

            else -> {
                // No location access granted.
                Log.e("CreatePostFragment", "No location access granted")
            }
        }
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                // Display the image
                imageView.setImageURI(uri)

                // Convert image to Base64
                base64Image = ImageUtils.convertImageToBase64(uri, requireContext())
            }
        }
    }


    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}