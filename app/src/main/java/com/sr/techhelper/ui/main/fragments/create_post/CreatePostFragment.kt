package com.sr.techhelper.ui.main.fragments.create_post

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.ui.main.PostsViewModel
import java.io.ByteArrayOutputStream


class CreatePostFragment : Fragment() {
    private val viewModel: PostsViewModel by activityViewModels()
    private var userUid: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var imageView: ImageView
    private lateinit var base64Image: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton: Button = view.findViewById(R.id.save_button)
        val cancelButton: Button = view.findViewById(R.id.cancel_button)
        val titleEditText = view.findViewById<EditText>(R.id.add_post_title_edit_text)
        val descriptionEditText = view.findViewById<EditText>(R.id.add_post_description_edit_text)
        imageView = view.findViewById<ImageView>(R.id.create_post_image)

        imageView.setOnClickListener {
            pickImageFromGallery()
        }

        cancelButton.setOnClickListener {
            val action = CreatePostFragmentDirections.actionCreatePostFragmentToPostListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        saveButton.setOnClickListener {
            val newPost = PostModel(
                userId = userUid,
                title = titleEditText?.text?.toString() ?: "",
                description = descriptionEditText?.text?.toString() ?: "",
                locationLat = 31.35,
                locationLng = 31.35,// Replace with actual location
                image = base64Image
            )
            viewModel.addPost(newPost)
            val action = CreatePostFragmentDirections.actionCreatePostFragmentToPostListFragment()
            Navigation.findNavController(it).navigate(action)
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
                base64Image = convertImageToBase64(uri)
            }
        }
    }

    private fun convertImageToBase64(uri: Uri): String {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)

        // Reduce the dimensions of the image
        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 480, 480, true) // Adjust width/height

        // Compress the Bitmap
        val compressedStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, compressedStream) // Lower quality to 70%
        val compressedByteArray = compressedStream.toByteArray()

        // Convert to Base64
        return Base64.encodeToString(compressedByteArray, Base64.DEFAULT)
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}


