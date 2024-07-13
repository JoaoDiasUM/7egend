package com.example.myapplication.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.remote.dto.User
import com.example.myapplication.data.room.Converters
import com.example.myapplication.data.room.dao.UserDao

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}