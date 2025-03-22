package com.sr.techhelper.data

import androidx.compose.ui.input.key.type
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StringListConverters {
    private val listType: Type = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        println("herrreee")
        return if (value == null) {
            null
        } else {
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        println("Not hereee")
        return if(list == null){
            null
        } else{
            Gson().toJson(list)
        }
    }
}