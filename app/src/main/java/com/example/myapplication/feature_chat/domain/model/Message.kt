package com.example.myapplication.feature_chat.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message")
data class Message(
    val attachments: List<Attachment>? = emptyList(),
    val content: String,
    @PrimaryKey val id: Int,
    val userId: Int
) {
    val isFromMe: Boolean
        get() = userId == 2
}