package com.sr.techhelper.data.students

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
    fun getAllStudents(): LiveData<List<StudentModel>>

    @Query("SELECT * FROM students WHERE id = :id")
    fun getById(id: String): LiveData<StudentModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg student: StudentModel)

    @Update
    fun update(student: StudentModel)

    @Delete
    fun delete(student: StudentModel)

    @Query("DELETE FROM students WHERE id = :id")
    fun deleteById(id: String)

    @Upsert
    fun upsertAll(vararg student: StudentModel)
}