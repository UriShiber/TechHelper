package com.sr.techhelper.data.students

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sr.techhelper.room.DatabaseHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class StudentsRepository {
    private val studentDao = DatabaseHolder.getDatabase().studentsDao()
    private val firestoreHandle = Firebase.firestore.collection("students")

    fun getAllStudents(): LiveData<List<StudentModel>> {
       return studentDao.getAllStudents()
    }

    suspend fun add(student: StudentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.add(student).await()
        studentDao.add(student)
    }

    suspend fun edit(student: StudentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(student.id).set(student).await()
        studentDao.update(student)
    }

    suspend fun delete(student: StudentModel) = withContext(Dispatchers.IO) {
        firestoreHandle.document(student.id).delete().await()
        studentDao.delete(student)
    }

    suspend fun deleteById(id: String) = withContext(Dispatchers.IO) {
        firestoreHandle.document(id).delete().await()
        studentDao.deleteById(id)
    }

    suspend fun loadStudentsFromRemoteSource(limit: Int, offset: Int) =
        withContext(Dispatchers.IO) {

            val students = firestoreHandle.orderBy("student").startAt(offset).limit(limit.toLong())
                .get().await().toObjects(StudentDTO::class.java).map { it.toStudentModel() }

            if (students.isNotEmpty()) {
                studentDao.upsertAll(*students.toTypedArray())

            }
        }
}