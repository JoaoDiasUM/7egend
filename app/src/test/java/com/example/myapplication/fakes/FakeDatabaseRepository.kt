package com.example.myapplication.fakes

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository

class FakeDatabaseRepository : DatabaseRepository {
    private val message = Message(emptyList(), "example", 2, 2)
    private val user = User("avatarId", 2, "Joao")

    override suspend fun getAllUsers(): List<User> {
        return listOf(user)
    }

    override suspend fun insertAllUsers(users: List<User>) {
    }

    override suspend fun insertMessage(message: Message) {
    }

    override suspend fun insertAllMessages(messages: List<Message>) {
    }

    override suspend fun getAllMessages(): List<Message> {
        return listOf(message)
    }
}