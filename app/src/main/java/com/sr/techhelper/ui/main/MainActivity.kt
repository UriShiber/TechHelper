package com.sr.techhelper.ui.main

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.error
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sr.techhelper.R
import com.sr.techhelper.data.users.UserModel

class MainActivity : AppCompatActivity() {

    private var currentUserModel: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Setup BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        // Load the user's profile image
        loadUserProfileImage(bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.postsListFragment)
                    true
                }
                R.id.add -> {
                    navController.navigate(R.id.createPostFragment)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profilePageFragment)
                    true
                }
                R.id.mapFragment -> {
                    navController.navigate(R.id.mapFragment)
                    true
                }
                else -> false
            }
        }
    }

    fun setUserModel(userModel: UserModel) {
        currentUserModel = userModel
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        loadUserProfileImage(bottomNavigationView)
    }

    private fun loadUserProfileImage(bottomNavigationView: BottomNavigationView) {
        // Get the profile MenuItem
        val profileMenuItem = bottomNavigationView.menu.findItem(R.id.profile)
        Log.d("MainActivity", "loadUserProfileImage: profileMenuItem: $profileMenuItem")

        // Use the profileImageUrl from the current user
        val userProfileImageUrl = currentUserModel?.profile_picture

        if (userProfileImageUrl == null) {
            // If no image URL, set the placeholder or do nothing
            Log.d("MainActivity", "loadUserProfileImage: no url")
            profileMenuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_account_circle)
            return
        }

        val placeholder = ContextCompat.getDrawable(this, R.drawable.ic_account_circle)!!

        Glide.with(this)
            .asBitmap() // We want a Bitmap
            .load(userProfileImageUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop())) // Make the image circular
            .placeholder(placeholder)
            .error(placeholder) // Fallback to drawable.empty_profile_picture
            .into(object : com.bumptech.glide.request.target.CustomTarget<android.graphics.Bitmap>() {
                override fun onResourceReady(resource: android.graphics.Bitmap, transition: com.bumptech.glide.request.transition.Transition<in android.graphics.Bitmap>?) {
                    // Set the Bitmap as the icon for the profile menu item
                    Log.d("MainActivity", "onResourceReady: setting icon, resource: $resource")
                    if (resource != null) {
                        profileMenuItem.icon = BitmapDrawable(resources, resource)
                    } else {
                        Log.e("MainActivity", "onResourceReady: resource is null!")
                    }
                    Log.d("MainActivity", "onResourceReady: icon set")
                }

                override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {
                    // If Glide clears the image
                    profileMenuItem.icon = placeholder
                    Log.d("MainActivity", "onLoadCleared")
                }
            })
    }
}