package com.example.myapplication.presentation.messaging

import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.remote.dto.User

class MessagingScreenState(
    val isLoading: Boolean = false,
    val messages: List<Message>? = null,
    val users: List<User>? = null,
    val error: String = ""
)