package com.example.myapplication.presentation.messaging

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.domain.usecase.GetMessagingHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MessagingViewModel @Inject constructor(
    private val getMessagingHistory: GetMessagingHistory,
) : ViewModel() {

    private val _state = mutableStateOf(MessagingScreenState())
    val state: State<MessagingScreenState> = _state

    init {
        getMessagesHistory()
    }

    private fun getMessagesHistory() {
        getMessagingHistory.invoke().onEach { result ->
            when (result) {
                is Resource.Error ->
                    _state.value = MessagingScreenState(
                        error = result.message ?: "An unexpected happened"
                    )

                is Resource.Loading -> _state.value = MessagingScreenState(isLoading = true)
                is Resource.Success -> _state.value = MessagingScreenState(
                    messages = result.data?.messages,
                    users = result.data?.users
                )
            }
        }.launchIn(viewModelScope)
    }

    fun getMessagesHistoryByUser(userId: Int): MutableList<Message> {
        val myMessages = state.value.messages?.filter { it.userId == 1 }
        val userMessages = state.value.messages?.filter { it.userId == userId }

        val combinedMessages = mutableListOf<Message>()
        combinedMessages.addAll(myMessages ?: emptyList())
        combinedMessages.addAll(userMessages ?: emptyList())
        combinedMessages.sortByDescending { it.id }

        return combinedMessages
    }
}