package com.sr.techhelper.ui.main.fragments.edit_student

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
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.data.students.StudentsRepository
import com.sr.techhelper.ui.main.StudentsViewModel
import com.sr.techhelper.utils.decodeBase64ToImage
import java.io.ByteArrayOutputStream


class EditStudentFragment : Fragment() {
    private val viewModel: StudentsViewModel by activityViewModels()
    private var studentUid: String = FirebaseAuth.getInstance().currentUser!!.uid

    private lateinit var imageView: ImageView
    private lateinit var base64Image: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentStudent: StudentModel ?= null
        val studentId = arguments?.getString("student_id")

        viewModel.getAllStudents().observe(viewLifecycleOwner, {
            if (it.isEmpty()) viewModel.invalidateStudents()

            val studentsList = it

            studentId?.let {
                val studentId = it
                currentStudent = studentsList.find { student -> student.id == studentId }
            }


            // Find the UI elements
            val cancelButton: Button = view.findViewById(R.id.cancel_button)
            val deleteButton: Button = view.findViewById(R.id.delete_button)
            val saveButton: Button = view.findViewById(R.id.save_button)

            val nameText: EditText = view.findViewById(R.id.student_row_name_value_text_view)
            val idText: EditText = view.findViewById(R.id.student_row_id_value_text_view)
            val phoneText: EditText = view.findViewById(R.id.student_row_phone_value_text_view)
            val addressText: EditText = view.findViewById(R.id.student_row_address_value_text_view)
            val checkBox: CheckBox = view.findViewById(R.id.student_row_check_box_value)
            imageView = view.findViewById<ImageView>(R.id.edit_student_image)

            currentStudent?.image?.let {
                val bitmap = decodeBase64ToImage(it)
                imageView.setImageBitmap(bitmap)
            }

            imageView.setOnClickListener {
                pickImageFromGallery()
            }

            // Check if student data is available and update the UI accordingly
            currentStudent?.let {
                Log.d("StudentEditActivity", "Populating fields with: $it")
                nameText.setText(it.name)
                idText.setText(it.id)
                phoneText.setText(it.phone)
                addressText.setText(it.address)
                checkBox.isChecked = it.isChecked
            }

            // Set the cancelButton action
            cancelButton.setOnClickListener {
                studentId?.let {
                    viewModel.deleteStudentById(studentId)
                }
                val action =
                    EditStudentFragmentDirections.actionEditStudentFragmentToStudentsListFragment()
                Navigation.findNavController(it).navigate(action)
            }

            // Set the deleteButton action
            deleteButton.setOnClickListener {
                studentId?.let {
                    viewModel.deleteStudentById(studentId)
                }
                val action =
                    EditStudentFragmentDirections.actionEditStudentFragmentToStudentsListFragment()
                Navigation.findNavController(it).navigate(action)
            }

            // Set the saveButton action
            saveButton.setOnClickListener {
                val updatedStudent = StudentModel(
                    name = nameText.text.toString(),
                    id = idText.text.toString(),
                    phone = phoneText.text.toString(),
                    address = addressText.text.toString(),
                    isChecked = checkBox.isChecked,
                    image = base64Image,
                )

                currentStudent?.let { oldStudent ->
                    val updatedStudent = oldStudent.copy(
                        name = nameText.text.toString(),
                        id = idText.text.toString(),
                        phone = phoneText.text.toString(),
                        address = addressText.text.toString(),
                        isChecked = checkBox.isChecked
                    )
                    viewModel.editStudent(updatedStudent)
                    val action =
                        EditStudentFragmentDirections.actionEditStudentFragmentToStudentDetailsFragment(studentId ?: "")
                    Navigation.findNavController(it).navigate(action)


                }
            }

        })
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

