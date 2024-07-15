package com.example.myapplication.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachment")
data class Attachment(
    @PrimaryKey val id: String,
    val thumbnailUrl: String,
    val title: String,
    val url: String,
    val userId: String,
)