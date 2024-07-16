package com.example.myapplication.feature_chat.domain.model

data class MessagingHistoryDto(
    val messages: List<Message>,
    val users: List<User>
)