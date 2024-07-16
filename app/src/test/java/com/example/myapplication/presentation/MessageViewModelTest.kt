package com.example.myapplication.presentation

import app.cash.turbine.test
import com.example.myapplication.CoroutineTestRule
import com.example.myapplication.fakes.FakeDatabaseRepository
import com.example.myapplication.fakes.FakeMessagingRepository
import com.example.myapplication.feature_chat.domain.model.Attachment
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.domain.model.User
import com.example.myapplication.feature_chat.domain.usecase.GetMessagingHistory
import com.example.myapplication.feature_chat.presentation.messaging.MessagingScreenState
import com.example.myapplication.feature_chat.presentation.messaging.MessagingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MessageViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule = CoroutineTestRule(testDispatcher)

    private lateinit var sut: MessagingViewModel
    private lateinit var getMessagingHistory: GetMessagingHistory

    private val databaseRepository = FakeDatabaseRepository()
    private val messagingRepository = FakeMessagingRepository()

    private val message = Message(emptyList(), "example", 2, 2)
    private val message2 = Message(emptyList(), "example", 3, 3)
    private val message3 = Message(emptyList(), "message", 1, 2)
    private val user = User("avatarId", 2, "Joao")

    @Before
    fun setup() {
        getMessagingHistory = GetMessagingHistory(messagingRepository)
        sut = MessagingViewModel(
            databasesRepository = databaseRepository,
            getMessagingHistory = getMessagingHistory,

            )
    }

    @Test
    fun `Add new text message`() =
        runTest {
            sut.state.value.messagesHistory = listOf(message3)

            val newMessage = Message(null, "message", 2, 2)

            sut.state.test {
                awaitItem()
                sut.addNewTextMessage("message")
                assertEquals(
                    awaitItem(),
                    MessagingScreenState(
                        currentTextMessage = "",
                        messages = listOf(message),
                        users = listOf(user),
                        messagesHistory = listOf(message3,newMessage)
                    )
                )
            }
        }

    @Test
    fun `Add new text message with attachment`() =
        runTest {
            sut.state.value.messagesHistory = listOf(message3)

            val attachment = Attachment(
                "2",
                "https://picsum.photos/200/300",
                "my image",
                "https://picsum.photos/200/300"
            )

            val newMessage = Message(listOf(attachment), "message", 2, 2)

            sut.state.test {
                awaitItem()
                sut.addNewMessageWithAttachment("message")
                assertEquals(
                    awaitItem(),
                    MessagingScreenState(
                        currentTextMessage = "",
                        messages = listOf(message),
                        users = listOf(user),
                        messagesHistory = listOf(message3,newMessage)
                    )
                )
            }
        }

    @Test
    fun `Update popup url`() =
        runTest {
            sut.state.test {
                sut.updatePopupUrl("popupUrl")
                awaitItem()
                assertEquals(
                    awaitItem(),
                    MessagingScreenState(
                        popupUrl = "popupUrl",
                        messages = listOf(message),
                        users = listOf(user)
                    )
                )
            }
        }

    @Test
    fun `Update current message value`() =
        runTest {
            sut.state.test {
                sut.updateCurrentTextMessageValue("message")
                awaitItem()
                assertEquals(
                    awaitItem(),
                    MessagingScreenState(
                        currentTextMessage = "message",
                        messages = listOf(message),
                        users = listOf(user)
                    )
                )
            }
        }

    @Test
    fun `Get messages by user`() =
        runTest {
            sut.state.value.messages = listOf(message,message2)

            sut.state.test {
                sut.getMessagesHistoryByUser(3)
                awaitItem()
                assertEquals(
                    awaitItem(),
                    MessagingScreenState(
                        messages = listOf(message,message2),
                        users = listOf(user),
                        messagesHistory = listOf(message,message2),
                    )
                )
            }
        }
}