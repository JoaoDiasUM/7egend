package com.example.myapplication.presentation.messaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Constants.MY_ID
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.data.remote.dto.User
import com.example.myapplication.domain.repository.DatabasesRepository
import com.example.myapplication.domain.usecase.GetMessagingHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagingViewModel @Inject constructor(
    private val getMessagingHistory: GetMessagingHistory,
    private val databasesRepository: DatabasesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MessagingScreenState())
    val state: StateFlow<MessagingScreenState> = _state

    init {
        getMessagesHistory()
    }

    private fun getMessagesHistory() {
        getMessagingHistory.invoke().onEach { result ->
            _state.update {
                when (result) {
                    is Resource.Error ->
                        it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )

                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> {
                        it.copy(
                            messages = result.data?.messages?.let { it1 -> saveMessagesToDB(it1) },
                            users = result.data?.users?.let { it1 -> saveUsersToDB(it1) },
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun saveUsersToDB(users: List<User>): List<User> {
        val updatedUsers = viewModelScope.async {
            databasesRepository.insertAllUsers(users)
            databasesRepository.getAllUsers()
        }
        return updatedUsers.await()
    }

    private suspend fun saveMessagesToDB(messages: List<Message>): List<Message> {
        val updatedAttachments = viewModelScope.async {
            databasesRepository.insertAllMessages(messages)
            databasesRepository.getAllMessages()
        }
        return updatedAttachments.await()
    }

    private suspend fun saveMessageToDB(message: Message) {
        databasesRepository.insertMessage(message)
    }

    fun addNewTextMessage(message: String) {
        val newMessageId = _state.value.messagesHistory?.maxBy { it.id }?.id?.plus(1) ?: 1
        val newMessage = Message(null, message, newMessageId, 2)

        val newList = _state.value.messagesHistory?.toMutableList()?.apply {
            add(newMessage)
        }

        _state.update {
            it.copy(
                currentTextMessage = "",
                messagesHistory = newList
            )
        }

        viewModelScope.launch {
           saveMessageToDB(newMessage)
        }
    }

    fun updateCurrentTextMessageValue(message: String) {
        _state.update {
           it.copy(
               currentTextMessage = message
           )
        }
    }

    fun updatePopupUrl(url: String) {
        _state.update {
            it.copy(
                popupUrl = url
            )
        }
    }

    fun getMessagesHistoryByUser(userId: Int) {
        val myMessages = _state.value.messages?.filter { it.userId == MY_ID }
        val userMessages = _state.value.messages?.filter { it.userId == userId }

        val combinedMessages = mutableListOf<Message>()
        combinedMessages.addAll(myMessages ?: emptyList())
        combinedMessages.addAll(userMessages ?: emptyList())
        combinedMessages.sortBy { it.id }

        _state.update {
            it.copy(
                messagesHistory = combinedMessages
            )
        }
    }
}