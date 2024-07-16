package com.example.myapplication.feature_chat.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.feature_chat.presentation.components.UserMessageItem
import com.example.myapplication.feature_chat.presentation.messaging.MessagingViewModel

@Composable
fun UserChatScreen(
    userId: Int,
    viewModel: MessagingViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()

    if(state.value.messagesHistory.isNullOrEmpty()){
        viewModel.getMessagesHistoryByUser(userId)
    }

    LaunchedEffect(state.value.messagesHistory?.size){
        lazyListState.animateScrollToItem(lazyListState.layoutInfo.totalItemsCount)
    }

    if (state.value.messagesHistory?.isNotEmpty() == true) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 50.dp, 20.dp, 50.dp)
    ) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true,
                state = lazyListState
            ) {
                state.value.messagesHistory?.forEach { message ->
                    item {
                        UserMessageItem(message = message, viewModel = viewModel)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.baseline_attach_file_24),
                    contentDescription = stringResource(id = R.string.app_name),
                )

                OutlinedTextField(
                    value = state.value.currentTextMessage,
                    label = { Text(LocalContext.current.getString(R.string.user_chat_label)) },
                    onValueChange = {
                        viewModel.updateCurrentTextMessageValue(it)
                    }
                )

                Image(
                    modifier = Modifier
                        .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        .clickable {
                            state.value.currentTextMessage.let {
                                viewModel.addNewTextMessage(
                                    it
                                )
                            }
                        },
                    painter = painterResource(id = R.drawable.baseline_send_24),
                    contentDescription = stringResource(id = R.string.app_name),
                )
            }
        }
    }
}
}