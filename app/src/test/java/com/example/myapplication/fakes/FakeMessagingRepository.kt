package com.example.myapplication.fakes

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.repository.MessagingRepository

class FakeMessagingRepository : MessagingRepository{

    private val message = Message(emptyList(),"message",2,2)
    private val user = User("avatarId",2,"Joao")

    override suspend fun getMessages(): MessagingHistoryDto {
        return MessagingHistoryDto(listOf(message), listOf(user))
    }
}