package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.MessagingHistoryDto

interface MessagingRepository {

    suspend fun getMessages(): MessagingHistoryDto
}