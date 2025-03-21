package com.sr.techhelper.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.MainActivity
import com.sr.techhelper.utils.ImageUtils
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {
    private val signInLauncher by lazy {
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { result ->
            lifecycleScope.launch {
                onSignInResult(result)
            }
        }
    }

    private val supportedAuth = arrayListOf(
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.EmailBuilder().build()
    )

    private val viewModel: AuthViewModel by viewModels<AuthViewModel> { ViewModelProvider.NewInstanceFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        AuthUI.getInstance().apply {
            if (FirebaseAuth.getInstance().currentUser != null) {
                toApp()
            } else {
                createSignInIntentBuilder()
                    .setAvailableProviders(supportedAuth)
                    .setIsSmartLockEnabled(false)
                    .setLogo(R.drawable.tech_helper)
                    .setTheme(R.style.Base_Theme_techhelper)
                    .build().apply {
                        signInLauncher.launch(this)
                    }
            }
        }

    }

    private suspend fun getUserImage(): String {
        val user = FirebaseAuth.getInstance().currentUser
        val photoUrl = user?.photoUrl

        // Convert photoUrl to Base64 string if available
        return if (photoUrl != null) {
            val test = ImageUtils.convertPhotoUrlToBase64(photoUrl.toString())
            test
        } else {
            val drawableImage = ImageUtils.convertDrawableToBase64(this, R.drawable.empty_profile_picture)
            drawableImage
        }
    }


    private suspend fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            val userImage = getUserImage()
            viewModel.register(::toApp,userImage )
        }
    }

    private fun toApp() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}