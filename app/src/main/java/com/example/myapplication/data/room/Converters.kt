package com.example.myapplication.data.room

import androidx.room.TypeConverter
import com.example.myapplication.data.remote.dto.Attachment
import com.google.gson.Gson



object Converters {
    @TypeConverter
    fun listToJson(value: List<Attachment>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Attachment>::class.java).toList()
}