package com.example.myapplication.feature_chat.data.repository

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.data.room.dao.MessageDao
import com.example.myapplication.feature_chat.data.room.dao.UserDao
import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabasesRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val messageDao: MessageDao
): DatabaseRepository {
    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    override suspend fun insertAllUsers(users: List<User>) {
        return userDao.insertAllUsers(users)
    }

    override suspend fun insertMessage(message: Message) {
        return messageDao.insertMessage(message)
    }

    override suspend fun insertAllMessages(messages: List<Message>) {
        return messageDao.insertAllMessages(messages)
    }

    override suspend fun getAllMessages(): List<Message> {
        return messageDao.getAllMessages()
    }
}