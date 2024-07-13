package com.example.myapplication.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.remote.dto.Attachment
import com.example.myapplication.data.room.Converters
import com.example.myapplication.data.room.dao.AttachmentDao

@Database(entities = [Attachment::class], version = 1)
@TypeConverters(Converters::class)
abstract class AttachmentDatabase : RoomDatabase() {
    abstract fun attachmentDao(): AttachmentDao
}