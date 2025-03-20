package com.sr.techhelper.ui.main.fragments.edit_post

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
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
import com.sr.techhelper.utils.decodeBase64ToImage
import java.io.ByteArrayOutputStream

class EditPostFragment : Fragment() {
    private val viewModel: PostsViewModel by activityViewModels()
    private var userUid: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var imageView: ImageView
    private var base64Image: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentPost: PostModel? = null
        val postId = arguments?.getString("post_id")

        viewModel.getAllPosts().observe(viewLifecycleOwner) { postsList ->
            if (postsList.isEmpty()) viewModel.invalidatePosts()

            postId?.let {
                currentPost = postsList.find { post -> post.id == it }
            }

            val cancelButton: Button = view.findViewById(R.id.cancel_button)
            val deleteButton: Button = view.findViewById(R.id.delete_button)
            val saveButton: Button = view.findViewById(R.id.save_button)

            val titleText: EditText = view.findViewById(R.id.add_post_title_edit_text)
            val descriptionText: EditText = view.findViewById(R.id.add_post_description_edit_text)
            imageView = view.findViewById(R.id.edit_post_image)

            currentPost?.image?.let {
                val bitmap = decodeBase64ToImage(it)
                imageView.setImageBitmap(bitmap)
            }

            imageView.setOnClickListener {
                pickImageFromGallery()
            }

            currentPost?.let {
                Log.d("EditPostFragment", "Populating fields with: $it")
                titleText.setText(it.title)
                descriptionText.setText(it.description)
            }

            cancelButton.setOnClickListener {
                val action = EditPostFragmentDirections.actionEditPostFragmentToPostsListFragment()
                Navigation.findNavController(it).navigate(action)
            }

            deleteButton.setOnClickListener {
                postId?.let { id ->
                    viewModel.deletePostById(id)
                }
                val action = EditPostFragmentDirections.actionEditPostFragmentToPostsListFragment()
                Navigation.findNavController(it).navigate(action)
            }

            saveButton.setOnClickListener {
                val updatedPost = currentPost?.copy(
                    title = titleText.text.toString(),
                    description = descriptionText.text.toString(),
                    image = base64Image ?: currentPost?.image
                )

                updatedPost?.let { post ->
                    viewModel.editPost(post)
                    val action = EditPostFragmentDirections.actionEditPostFragmentToPostDetailsFragment(postId ?: "")
                    Navigation.findNavController(it).navigate(action)
                }
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
                imageView.setImageURI(uri)
                base64Image = convertImageToBase64(uri)
            }
        }
    }

    private fun convertImageToBase64(uri: Uri): String {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 480, 480, true)

        val compressedStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, compressedStream)
        val compressedByteArray = compressedStream.toByteArray()

        return Base64.encodeToString(compressedByteArray, Base64.DEFAULT)
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}
