package com.sr.techhelper.ui.main.fragments.student_details
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.sr.techhelper.R
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.ui.main.StudentsViewModel
import com.sr.techhelper.utils.decodeBase64ToImage


class StudentDetailsFragment : Fragment() {
    private val viewModel: StudentsViewModel by activityViewModels()
    private var studentUid: String = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentStudent : StudentModel ?= null
        val studentId = arguments?.getString("student_id")

        viewModel.getAllStudents().observe(viewLifecycleOwner, {
            if(it.isEmpty()) viewModel.invalidateStudents()
            val studentsList = it

            studentId?.let {
                val studentId = it
                currentStudent = studentsList.find { student -> student.id == studentId}
 
            }

            val nameText: TextView = view.findViewById(R.id.student_row_name_value_text_view)
            val idText: TextView = view.findViewById(R.id.student_row_id_value_text_view)
            val phoneText: TextView = view.findViewById(R.id.student_row_phone_value_text_view)
            val addressText: TextView = view.findViewById(R.id.student_row_address_value_text_view)
            val checkBox: CheckBox = view.findViewById(R.id.student_row_check_box_value)
            val image = view.findViewById<ImageView>(R.id.edit_student_image)
            val editButton: Button = view.findViewById(R.id.edit_button)
            val backButton: AppCompatImageButton = view.findViewById(R.id.back_button)

            // Update the UI with the new student data
            nameText.text = currentStudent?.name
            idText.text = currentStudent?.id.toString()
            phoneText.text = currentStudent?.phone
            addressText.text = currentStudent?.address
            checkBox.isChecked = currentStudent?.isChecked ?: false


            editButton.setOnClickListener {
                val action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(studentId ?: "")
                Navigation.findNavController(it).navigate(action)
            }

            backButton.setOnClickListener {
                val action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentsListFragment()
                Navigation.findNavController(it).navigate(action)
            }

            currentStudent?.image?.let {
                val bitmap = decodeBase64ToImage(it)
                image.setImageBitmap(bitmap)
            }


        })
    }
}

