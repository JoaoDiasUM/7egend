package com.example.myapplication.feature_chat.presentation.messaging

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User

data class MessagingScreenState(
    val isLoading: Boolean = false,
    val messages: List<Message>? = null,
    val users: List<User>? = null,
    val messagesHistory: List<Message>? = null,
    val currentTextMessage: String = "",
    val error: String = "",
    val popupUrl: String? = null
)