package com.sr.techhelper.room

import android.content.Context
import androidx.room.Room

object DatabaseHolder {
    @Volatile
    private var appDatabase: AppDatabase? = null

    fun initDatabase(context: Context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "just-eat-db").fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase {
        return this.appDatabase!!
    }
}