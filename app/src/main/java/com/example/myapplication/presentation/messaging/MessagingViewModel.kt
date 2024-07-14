package com.example.myapplication.presentation.messaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Constants.MY_ID
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.domain.usecase.GetMessagingHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MessagingViewModel @Inject constructor(
    private val getMessagingHistory: GetMessagingHistory,
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
                    is Resource.Success -> it.copy(
                        messages = result.data?.messages,
                        users = result.data?.users,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun updateCurrentTextMessageValue(message: String) {
        _state.update {
           it.copy(
               currentTextMessage = message
           )
        }
    }

    fun getMessagesHistoryByUser(userId: Int) {
        val myMessages = state.value.messages?.filter { it.userId == MY_ID }
        val userMessages = state.value.messages?.filter { it.userId == userId }

        val combinedMessages = mutableListOf<Message>()
        combinedMessages.addAll(myMessages ?: emptyList())
        combinedMessages.addAll(userMessages ?: emptyList())
        combinedMessages.sortByDescending { it.id }

        _state.update {
            it.copy(
                messagesHistory = combinedMessages
            )
        }
    }
}