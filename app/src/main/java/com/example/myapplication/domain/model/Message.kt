package com.example.myapplication.domain.model

import com.example.myapplication.data.remote.dto.Attachment

data class Message(
    val attachments: List<Attachment>,
    val content: String,
    val id: Int,
    val userId: Int
)