package com.example.myapplication.presentation.messaging

import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.remote.dto.User

data class MessagingScreenState(
    val isLoading: Boolean = false,
    val messages: List<Message>? = null,
    val users: List<User>? = null,
    val messagesHistory: List<Message>? = null,
    val currentTextMessage: String = "",
    val error: String = "",
    val popupUrl: String? = null
)