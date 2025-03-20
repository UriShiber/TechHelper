package com.sr.techhelper.ui.main.fragments.posts_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.StudentsViewModel


class StudentsListFragment : Fragment() {
    private lateinit var studentsList: RecyclerView
    private val viewModel: StudentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentsList = view.findViewById(R.id.students_list)
        context?.let { initStudentsList(it) }
        viewModel.getAllStudents().observe(viewLifecycleOwner, {
            if(it.isEmpty()) viewModel.invalidateStudents()
            (studentsList.adapter as? StudentsAdapter)?.updateStudents(it)
        })
    }

    private fun initStudentsList(context: Context) {
        studentsList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = StudentsAdapter{ student ->
               val action = StudentsListFragmentDirections.actionStudentsListFragmentToStudentDetailsFragment(student.id)
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
}