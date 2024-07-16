package com.example.myapplication.feature_chat.domain.usecase

import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository
import javax.inject.Inject

class InsertMessage @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(message: Message) = databaseRepository.insertMessage(message)
}