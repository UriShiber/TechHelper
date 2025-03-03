package com.sr.techhelper.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.data.students.StudentsRepository
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class StudentsUiState(val reviewId: String = "")

class StudentsViewModel : ViewModel() {
    private val repository = StudentsRepository()
    private val usersRepository = UsersRepository()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadStudentsFromRemoteSource(50, 0)
        }
    }

    fun getAllStudents(): LiveData<List<StudentModel>> {
        return this.repository.getAllStudents()
    }


    fun getUserById(id: String): LiveData<UserModel?> {
        val userLiveData = MutableLiveData<UserModel?>()
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                usersRepository.getUserByUid(id)
            }
            userLiveData.postValue(user)
        }
        return userLiveData
    }

    fun updateUser(
        user: UserModel,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            usersRepository.upsertUser(user)
        }
    }

    fun addStudent(
        student: StudentModel,
    ) {
            viewModelScope.launch(Dispatchers.Main) {
                repository.add(student)
            }
        }

    fun editStudent(
        student: StudentModel,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.edit(student)
        }
    }

    fun invalidateStudents() {
        viewModelScope.launch {
            repository.loadStudentsFromRemoteSource(50, 0)
        }
    }

    fun deleteStudentById(
        studentId: String,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.deleteById(studentId)
        }
    }



}