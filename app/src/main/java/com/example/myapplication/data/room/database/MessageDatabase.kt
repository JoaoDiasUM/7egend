package com.example.myapplication.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.room.Converters
import com.example.myapplication.data.room.dao.MessageDao

@Database(entities = [Message::class], version = 1)
@TypeConverters(Converters::class)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}