package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.Message

interface MessagingRepository {

    suspend fun getMessages(): List<Message>
}