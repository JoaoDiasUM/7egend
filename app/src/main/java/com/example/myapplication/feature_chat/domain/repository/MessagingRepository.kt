package com.example.myapplication.feature_chat.domain.repository

import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto

interface MessagingRepository {

    suspend fun getMessages(): MessagingHistoryDto?
}