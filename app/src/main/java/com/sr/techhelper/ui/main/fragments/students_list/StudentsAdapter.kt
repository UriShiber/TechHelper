package com.sr.techhelper.ui.main.fragments.students_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sr.techhelper.R
import com.sr.techhelper.data.students.StudentModel

class StudentsAdapter(
    private val onStudentClick: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {
    private var students: List<StudentModel>  = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflator = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(inflator)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.student_row_name_text_view)
        private val studentId: TextView = itemView.findViewById(R.id.student_row_id_text_view)
        private val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)

        fun bind(student: StudentModel) {
            studentName.text = student.name
            studentId.text = student.id
            checkBox.isChecked = student.isChecked

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }

            itemView.setOnClickListener {
                onStudentClick(student)
            }
        }
    }

    fun updateStudents(newStudents: List<StudentModel>) {
        this.students = newStudents
        notifyDataSetChanged();
    }
}
