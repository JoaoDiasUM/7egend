package com.example.myapplication.domain.usecases

import app.cash.turbine.test
import com.example.myapplication.common.Resource
import com.example.myapplication.fakes.FakeMessagingRepository
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.MessagingHistoryDto
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.usecase.GetMessagingHistory
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class GetMessagingHistoryUseCaseTest {

    private lateinit var sut: GetMessagingHistory
    private var fakeMessagingRepository = FakeMessagingRepository()
    private val user = User("avatarId", 2, "Joao")
    private val message = Message(emptyList(), "message", 2, 2)

    @Before
    fun setUp() {
        sut = GetMessagingHistory(
            fakeMessagingRepository
        )
    }

    @Test
    fun `Get all messages from database`() = runTest {
        val expected = MessagingHistoryDto(listOf(message), listOf(user))
        sut.invoke().test {
            val result = awaitItem().data
            assertEquals(result, expected)
            awaitComplete()
        }
    }
}