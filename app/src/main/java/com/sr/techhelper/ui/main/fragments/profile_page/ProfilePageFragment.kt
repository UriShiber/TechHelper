package com.sr.techhelper.ui.main.fragments.profile_page

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.content.Intent
import android.provider.MediaStore
import android.util.Base64
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
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.fragments.students_list.StudentsAdapter
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.ui.auth.AuthActivity
import com.sr.techhelper.ui.main.StudentsViewModel
import com.sr.techhelper.utils.decodeBase64ToImage
import java.io.ByteArrayOutputStream


class ProfilePageFragment : Fragment() {
    private lateinit var studentsList: RecyclerView
    private val viewModel: StudentsViewModel by activityViewModels()
    private var reviewerUid: String = FirebaseAuth.getInstance().currentUser!!.uid

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
        val usernameText = view.findViewById<TextView>(R.id.username_text)
        imageView = view.findViewById<ImageView>(R.id.profile_picture)
        viewModel.getUserById(reviewerUid).observe(viewLifecycleOwner) { user ->
            user?.let {
                mainUser = it
                usernameText.text = it.name ?: "Guest"

                if(it.profile_picture != "") {
                    imageView.setImageBitmap(decodeBase64ToImage(it.profile_picture ?: ""))
                } else {
                    val googleImage = FirebaseAuth.getInstance().currentUser?.photoUrl
                    if(googleImage !== null) {
                        Glide.with(this) // 'this' can be a Fragment or Activity context
                            .load(googleImage)
                            .into(imageView)
                        imageView.setImageURI(googleImage)
                    }
                }




                imageView.setOnClickListener {
                    pickImageFromGallery()
                }
            } ?: run {
                // Handle user not found
                usernameText.text = "Guest"
            }
        }

        val logout = view.findViewById<Button>(R.id.profile_page_logout_button)
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }



//        studentsList = view.findViewById(R.id.profile_page_posts_list)
        context?.let { initStudentsList(it) }
        viewModel.getAllStudents().observe(viewLifecycleOwner, {
            it?.let {
                if(it.isEmpty()) viewModel.invalidateStudents()
                (studentsList.adapter as? StudentsAdapter)?.updateStudents(it)
            }

        })
    }


    private fun initStudentsList(context: Context) {
        studentsList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = StudentsAdapter{ student ->
                val action = ProfilePageFragmentDirections.actionProfilePageFragmentToStudentDetailsFragment(student.id)
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
                base64Image = convertImageToBase64(uri)



                mainUser?.let {
                    val newUser = UserModel(id = mainUser!!.id, profile_picture = base64Image, name = mainUser!!.name, email = mainUser!!.email)
                    viewModel.updateUser(newUser)
                }


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
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, compressedStream) // Lower quality to 30%
        val compressedByteArray = compressedStream.toByteArray()

        // Convert to Base64
        return Base64.encodeToString(compressedByteArray, Base64.DEFAULT)
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}