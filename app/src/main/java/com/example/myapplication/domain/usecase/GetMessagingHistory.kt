package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.remote.dto.MessagingHistoryDto
import com.example.myapplication.domain.repository.MessagingRepository
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
            emit(Resource.Loading())
            val messages = messagingRepository.getMessages()
            emit(Resource.Success(messages))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "An server error error happened"))
        }
    }
}