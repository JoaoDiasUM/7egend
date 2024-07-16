package com.example.myapplication.domain.usecases

import com.example.myapplication.fakes.FakeDatabaseRepository
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.usecase.GetAllUsers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllUsersUseCaseTest {

    private lateinit var sut: GetAllUsers
    private var fakeDatabaseRepository = FakeDatabaseRepository()
    private val user = User("avatarId", 2, "Joao")

    @Before
    fun setUp() {
        sut = GetAllUsers(
            databaseRepository = fakeDatabaseRepository
        )
    }

    @Test
    fun `Get all messages from database`() =
        runTest {
            val result = sut.invoke()
            assert(result == listOf(user))
        }
}