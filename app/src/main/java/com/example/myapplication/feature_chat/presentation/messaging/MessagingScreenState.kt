package com.example.myapplication.feature_chat.presentation.messaging

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User

data class MessagingScreenState(
    val isLoading: Boolean = false,
    var messages: List<Message>? = null,
    val users: List<User>? = null,
    var messagesHistory: List<Message>? = null,
    val currentTextMessage: String = "",
    val error: String = "",
    val popupUrl: String? = null
)