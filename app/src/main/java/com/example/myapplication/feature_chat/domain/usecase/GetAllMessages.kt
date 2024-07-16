package com.example.myapplication.feature_chat.domain.usecase

import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository
import javax.inject.Inject

class GetAllMessages @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() =
        databaseRepository.getAllMessages()
}