package com.sr.techhelper.ui.main.fragments.create_student

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
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.ui.main.StudentsViewModel
import java.io.ByteArrayOutputStream


class CreateStudentFragment : Fragment() {
    private val viewModel: StudentsViewModel by activityViewModels()
    private var studentsUid: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var imageView: ImageView
    private lateinit var base64Image: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton: Button = view.findViewById(R.id.save_button)
        val cancelButton: Button = view.findViewById(R.id.cancel_button)
        // TODO: rename add_student_activity_name_edit_text to just add student
        val nameEditText = view.findViewById<TextView>(R.id.add_student_activity_name_edit_text)
        val idEditText = view.findViewById<TextView>(R.id.add_student_activity_id_edit_text)
        val phoneEditText = view.findViewById<TextView>(R.id.add_student_activity_phone_edit_text)
        val addressEditText = view.findViewById<TextView>(R.id.add_student_activity_address_edit_text)
        val checkedCheckbox: CheckBox = view.findViewById(R.id.add_student_activity_checked_check_box)
        imageView = view.findViewById<ImageView>(R.id.create_student_image)

        imageView.setOnClickListener {
            pickImageFromGallery()
        }

        cancelButton.setOnClickListener{
            val action = CreateStudentFragmentDirections.actionCreateStudentFragmentToStudentsListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        saveButton.setOnClickListener {
            val newStudent =
                StudentModel(
                    id = idEditText?.text?.toString() ?: "",
                    name = nameEditText?.text?.toString() ?: "",
                    address = addressEditText?.text?.toString() ?: "",
                    phone = phoneEditText?.text?.toString() ?: "",
                    isChecked = checkedCheckbox?.isChecked ?: false,


                )
            viewModel.addStudent(newStudent)
            val action = CreateStudentFragmentDirections.actionCreateStudentFragmentToStudentsListFragment()
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
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, compressedStream) // Lower quality to 30%
        val compressedByteArray = compressedStream.toByteArray()

        // Convert to Base64
        return Base64.encodeToString(compressedByteArray, Base64.DEFAULT)
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001
    }
}

