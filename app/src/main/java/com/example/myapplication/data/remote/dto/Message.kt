package com.example.myapplication.data.remote.dto

data class Message(
    val attachments: List<Attachment>,
    val content: String,
    val id: Int,
    val userId: Int
)