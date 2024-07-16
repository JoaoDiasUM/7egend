package com.example.myapplication.feature_chat.domain.usecase

data class ChatUseCases(
    val getAllUsers: GetAllUsers,
    val getAllMessages: GetAllMessages,
    val insertMessage: InsertMessage,
    val insertAllUsers: InsertAllUsers,
    val insertAllMessages: InsertAllMessages,
)