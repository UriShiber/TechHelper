package com.sr.techhelper.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sr.techhelper.data.posts.PostDao
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersDao

@Database(
    entities = [PostModel::class, UserModel::class], version = 4, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun postDao(): PostDao

}