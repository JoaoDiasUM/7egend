package com.example.myapplication.feature_chat.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.data.room.ListConverters
import com.example.myapplication.feature_chat.data.room.dao.MessageDao

@Database(entities = [Message::class], version = 1)
@TypeConverters(ListConverters::class)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}