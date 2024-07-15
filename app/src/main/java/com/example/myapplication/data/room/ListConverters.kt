package com.example.myapplication.data.room

import androidx.room.TypeConverter
import com.example.myapplication.data.remote.dto.Attachment
import com.example.myapplication.data.remote.dto.Message
import com.google.gson.Gson


object ListConverters {
    @TypeConverter
    fun listToJson(value: List<Attachment>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String?): List<Attachment> {
        return if (value != null && value != "null") {
            Gson().fromJson(value, Array<Attachment>::class.java).toList()
        } else {
            emptyList()
        }
    }
}