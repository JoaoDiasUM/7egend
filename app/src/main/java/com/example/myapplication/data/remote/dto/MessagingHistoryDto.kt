package com.example.myapplication.data.remote.dto

data class MessagingHistoryDto(
    val messages: List<Message>,
    val users: List<User>
)