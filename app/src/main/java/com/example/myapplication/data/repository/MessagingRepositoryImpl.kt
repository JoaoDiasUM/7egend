package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.MessagingApiService
import com.example.myapplication.data.remote.dto.Message


import com.example.myapplication.domain.repository.MessagingRepository
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val messagingApiService: MessagingApiService
) : MessagingRepository {
    override suspend fun getMessages(): List<Message> {
        return messagingApiService.getConversationHistory().messages
    }
}