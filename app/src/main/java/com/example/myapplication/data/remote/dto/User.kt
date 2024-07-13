package com.example.myapplication.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val avatarId: String,
    @PrimaryKey val id: Int,
    val name: String
)