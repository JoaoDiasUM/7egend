package com.example.myapplication.domain.usecases

import com.example.myapplication.fakes.FakeDatabaseRepository
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.usecase.GetAllMessages
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllMessagesUseCaseTest {

    private lateinit var sut: GetAllMessages
    private var fakeDatabaseRepository = FakeDatabaseRepository()

    private val message = Message(emptyList(), "example", 2, 2)
    @Before
    fun setUp() {
        sut = GetAllMessages(
            databaseRepository = fakeDatabaseRepository
        )
    }

    @Test
    fun `Get all messages from database`() =
        runTest {
            val result = sut.invoke()
            assert(result == listOf(message))
        }
}