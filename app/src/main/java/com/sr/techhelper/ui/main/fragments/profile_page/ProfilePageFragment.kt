package com.sr.techhelper.ui.main.fragments.profile_page

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.content.Intent
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager


import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.fragments.posts_list.PostsAdapter
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.ui.auth.AuthActivity
import com.sr.techhelper.ui.main.PostsViewModel
import com.sr.techhelper.utils.ImageUtils


class ProfilePageFragment : Fragment() {
    private lateinit var postsList: RecyclerView
    private val viewModel: PostsViewModel by activityViewModels()
    private var userId: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var imageView: ImageView
    private lateinit var base64Image: String
    private var mainUser: UserModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameEditText = view.findViewById<TextView>(R.id.username_edit_text)
        imageView = view.findViewById<ImageView>(R.id.profile_picture)
        viewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            user?.let {
                mainUser = it
                usernameEditText.text = it.name
                Log.d("ProfilePageFragment", "Populating fields with: $it")
                if (!it.profile_picture.isNullOrEmpty()) {
                    val bitmap = ImageUtils.decodeBase64ToImage(it.profile_picture)
                    imageView.setImageBitmap(bitmap)
                } else {
                    imageView.setImageResource(R.drawable.empty_profile_picture)
                }
//                if(it.profile_picture != "") {
//                    imageView.setImageBitmap(ImageUtils.decodeBase64ToImage(it.profile_picture))
//                } else {
//                    imageView.setImageResource(R.drawable.empty_profile_picture)
//                }

                imageView.setOnClickListener {
                    pickImageFromGallery()
                }
            } ?: run {
                // Handle user not found
                usernameEditText.text = "Guest"
            }
        }

        val logout = view.findViewById<Button>(R.id.profile_page_logout_button)
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        val saveButton = view.findViewById<Button>(R.id.save_changes_button)
        saveButton.visibility = View.GONE // Initially hide the save button

        saveButton.setOnClickListener {
            val newUsername = usernameEditText.text.toString()
            mainUser?.let {
                val newUser = UserModel(id = mainUser!!.id, profile_picture = mainUser!!.profile_picture, name = newUsername, email = mainUser!!.email)
                viewModel.updateUser(newUser)
            }
            saveButton.visibility = View.GONE
            logout.visibility = View.VISIBLE
        }

        usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newUsername = s.toString()
                if (newUsername != "" && newUsername != mainUser!!.name) {
                    saveButton.visibility = View.VISIBLE
                    logout.visibility = View.GONE
                } else {
                    saveButton.visibility = View.GONE
                    logout.visibility = View.VISIBLE
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        postsList = view.findViewById(R.id.profile_page_posts_list)
        context?.let { initPostsList(it) }
        viewModel.getPostsByUserId(userId).observe(viewLifecycleOwner, {
            it?.let {
                if(it.isEmpty()) viewModel.invalidatePosts()
                (postsList.adapter as? PostsAdapter)?.updatePosts(it)
            }

        })
    }


    private fun initPostsList(context: Context) {
        postsList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = PostsAdapter{ post ->
                val action = ProfilePageFragmentDirections.actionProfilePageFragmentToPostDetailsFragment(post.post.id)
                findNavController().navigate(action)
            }
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
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



                mainUser?.let {
                    val newUser = UserModel(id = mainUser!!.id, profile_picture = base64Image, name = mainUser!!.name, email = mainUser!!.email)
                    viewModel.updateUser(newUser)
                }


            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}