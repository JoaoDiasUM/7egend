package com.example.myapplication.presentation.messaging

import com.example.myapplication.data.remote.dto.Message

class MessagingScreenState(
    val isLoading: Boolean = false,
    val messages: List<Message>? = null,
    val error: String = ""
)