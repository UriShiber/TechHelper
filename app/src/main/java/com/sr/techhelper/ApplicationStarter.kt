package com.sr.techhelper

import android.app.Application
import com.sr.techhelper.room.DatabaseHolder

class ApplicationStarter: Application() {
    override fun onCreate() {
        DatabaseHolder.initDatabase(this)
        super.onCreate()
    }
}