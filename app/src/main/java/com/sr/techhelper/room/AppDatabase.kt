package com.sr.techhelper.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sr.techhelper.data.StringListConverters
import com.sr.techhelper.data.comments.CommentDao
import com.sr.techhelper.data.comments.CommentModel
import com.sr.techhelper.data.posts.PostDao
import com.sr.techhelper.data.posts.PostModel
import com.sr.techhelper.data.users.UserModel
import com.sr.techhelper.data.users.UsersDao

@Database(
    entities = [PostModel::class, UserModel::class, CommentModel::class], version = 12, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}