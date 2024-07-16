package com.example.myapplication.feature_chat.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.data.room.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}