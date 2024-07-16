package com.example.myapplication.feature_chat.data.repository


import com.example.myapplication.feature_chat.data.remote.MessagingApiService
import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import com.example.myapplication.feature_chat.domain.repository.MessagingRepository
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val messagingApiService: MessagingApiService
) : MessagingRepository {
    override suspend fun getMessages(): MessagingHistoryDto {
        return messagingApiService.getConversationHistory()
    }
}