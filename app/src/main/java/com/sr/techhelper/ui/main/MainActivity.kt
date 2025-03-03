package com.sr.techhelper.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.fragments.students_list.StudentsAdapter
import com.sr.techhelper.data.students.StudentsRepository
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.ui.main.fragments.AddStudentActivity
import com.sr.techhelper.ui.main.fragments.StudentDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentsAdapter: StudentsAdapter
    private var studentsList = mutableListOf<StudentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_activity_add_student_button)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initStudents()
        onCreateStudentButton()

    }
    private fun onCreateStudentButton() {
        val addStudentButton: Button = findViewById(R.id.main_activity_add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initStudents() {
        StudentsRepository.shared.getAllStudents { students ->
            studentsList.clear()
            studentsList.addAll(students)

            recyclerView = findViewById(R.id.students_list)
            recyclerView.layoutManager = LinearLayoutManager(this)

            studentsAdapter = StudentsAdapter(studentsList) { student ->
                openStudentDetails(student)
            }
            recyclerView.adapter = studentsAdapter

            studentsAdapter.notifyDataSetChanged()
        }
    }

    private fun openStudentDetails(student: StudentModel) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("student", student)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        StudentsRepository.shared.getAllStudents { students ->
            studentsList.clear()
            studentsList.addAll(students)
            studentsAdapter.notifyDataSetChanged()

        }
    }
}

