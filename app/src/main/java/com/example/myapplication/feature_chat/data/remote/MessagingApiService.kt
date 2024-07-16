package com.example.myapplication.feature_chat.data.remote

import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import retrofit2.Response
import retrofit2.http.GET

interface MessagingApiService {
    @GET("conversation")
    suspend fun getConversationHistory(): Response<MessagingHistoryDto>
}