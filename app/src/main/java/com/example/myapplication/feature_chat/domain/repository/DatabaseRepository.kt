package com.example.myapplication.feature_chat.domain.repository

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User

interface DatabaseRepository {
    suspend fun getAllUsers(): List<User>

    suspend fun insertAllUsers(users: List<User>)

    suspend fun insertMessage(message: Message)

    suspend fun insertAllMessages(messages: List<Message>)

    suspend fun getAllMessages(): List<Message>
}