package com.example.myapplication.feature_chat.domain.usecase

import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository
import javax.inject.Inject

class InsertAllUsers @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(users: List<User>) = databaseRepository.insertAllUsers(users)
}