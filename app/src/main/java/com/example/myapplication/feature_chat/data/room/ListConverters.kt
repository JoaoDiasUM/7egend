package com.example.myapplication.feature_chat.data.room

import androidx.room.TypeConverter
import com.example.myapplication.feature_chat.domain.model.Attachment
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