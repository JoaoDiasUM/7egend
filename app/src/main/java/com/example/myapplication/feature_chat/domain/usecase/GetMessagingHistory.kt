package com.example.myapplication.feature_chat.domain.usecase

import com.example.myapplication.common.Resource
import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import com.example.myapplication.feature_chat.domain.repository.MessagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMessagingHistory @Inject constructor(
    private val messagingRepository: MessagingRepository
) {

    operator fun invoke(): Flow<Resource<MessagingHistoryDto>> = flow {
        try {
            val messages = messagingRepository.getMessages()
            if (messages != null) {
                emit(Resource.Success(messages))
            } else {
                emit(Resource.Error("No messages found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An server error error happened"))
        }
    }
}