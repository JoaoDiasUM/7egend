package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.MessagingHistoryDto
import retrofit2.http.GET

interface MessagingApiService {
    @GET("conversation")
    suspend fun getConversationHistory(): MessagingHistoryDto
}