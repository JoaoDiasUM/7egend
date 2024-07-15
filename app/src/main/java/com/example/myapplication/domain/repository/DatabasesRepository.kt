package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.remote.dto.User
import com.example.myapplication.data.room.dao.MessageDao
import com.example.myapplication.data.room.dao.UserDao
import javax.inject.Inject

class DatabasesRepository @Inject constructor(
    private val userDao: UserDao,
    private val messageDao: MessageDao
) {
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun insertAllUsers(users: List<User>) {
        return userDao.insertAllUsers(users)
    }

    suspend fun insertMessage(message: Message) {
        return messageDao.insertMessage(message)
    }

    suspend fun insertAllMessages(messages: List<Message>) {
        return messageDao.insertAllMessages(messages)
    }

    suspend fun getAllMessages(): List<Message> {
        return messageDao.getAllMessages()
    }
}