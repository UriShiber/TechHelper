package com.sr.techhelper.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sr.techhelper.R
import com.sr.techhelper.ui.main.fragments.students_list.StudentsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Load the fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentsListFragment()) // Replace with your container ID
                .commit()
        }
    }
}

