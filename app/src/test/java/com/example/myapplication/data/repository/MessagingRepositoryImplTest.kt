package com.example.myapplication.data.repository

import com.example.myapplication.feature_chat.data.remote.MessagingApiService
import com.example.myapplication.feature_chat.data.repository.MessagingRepositoryImpl
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import com.example.myapplication.feature_chat.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MessagingRepositoryImplTest {

    private lateinit var sut: MessagingRepositoryImpl
    private val messagingService = mockk<MessagingApiService>()
    private val errorResponseBody =
        ERROR_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull())
    @Before
    fun setUp() {
        sut = MessagingRepositoryImpl(messagingService)
    }

    @Test
    fun `When user retrieves messages, returns messages list`() = runTest {

        val exampleMessage = Message(
            emptyList(), "message", 1, 1
        )

        val exampleUser = User("",1,"Joao")

        val response = Response.success(
            MessagingHistoryDto(listOf(exampleMessage), listOf(exampleUser))
        )

        coEvery {
            messagingService.getConversationHistory()
        }.returns(response)

        val result = sut.getMessages()
        assert(result?.messages?.isNotEmpty() == true && result.users.isNotEmpty())
    }

    @Test
    fun `When user retrieves messages, returns error`() = runTest {
        val mockResponse = Response.error<MessagingHistoryDto>(400, errorResponseBody)

        coEvery {
            messagingService.getConversationHistory()
        }.returns(mockResponse)

        val result = sut.getMessages()
        assert(result == null)
    }

    companion object {
        const val ERROR_RESPONSE = "{\n" +
                "  \"type\": \"error\",\n" +
                "  \"message\": \"What you were looking for isn't here.\"\n}"
    }
}