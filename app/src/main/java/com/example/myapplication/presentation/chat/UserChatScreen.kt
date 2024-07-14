package com.example.myapplication.presentation.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.components.UserMessageItem
import com.example.myapplication.presentation.messaging.MessagingViewModel

@Composable
fun UserChatScreen(
    userId: Int,
    viewModel: MessagingViewModel = hiltViewModel(),
) {
    val messages = viewModel.getMessagesHistoryByUser(userId)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true
            ) {
                messages.forEach { message ->
                    item {
                        UserMessageItem(message = message)
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null
                    )
                },
                value = "",
                label = { Text("Tap Icon to send text") },
                onValueChange = {}
            )
        }
    }
}