package com.sr.techhelper.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sr.techhelper.data.students.StudentDao
import com.sr.techhelper.data.students.StudentModel
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersDao

@Database(
    entities = [StudentModel::class, UserModel::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun studentsDao(): StudentDao

}