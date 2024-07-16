package com.example.myapplication.feature_chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.feature_chat.domain.model.Message
import com.example.myapplication.feature_chat.presentation.chat.PopupBox
import com.example.myapplication.feature_chat.presentation.messaging.MessagingViewModel

@Composable
fun UserMessageItem(message: Message, viewModel: MessagingViewModel) {

    val isFromMe = message.isFromMe

    val alignment = if (message.isFromMe) Alignment.End else Alignment.Start

    val messageColor = if (isFromMe) Color.Blue else Color.Gray

    var showPopup by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .align(alignment)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (isFromMe) 48f else 0f,
                        bottomEnd = if (isFromMe) 0f else 48f
                    )
                )
                .background(messageColor)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.width(200.dp),
                text = message.content,
                color = Color.White
            )
        }

        message.attachments?.let {
            for (attachment in message.attachments) {
                AsyncImage(
                    model = ImageRequest.Builder(
                        LocalContext.current
                    )
                        .data(attachment.thumbnailUrl)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp)
                        .fillMaxWidth()
                        .align(alignment)
                        .clickable {
                            showPopup = true
                            viewModel.updatePopupUrl(attachment.url)
                        },
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_launcher_background)
                )
            }
        }
    }

    viewModel.state.collectAsState().value.popupUrl?.let {
        PopupBox(
            it,
            showPopup = showPopup,
            onClickOutside = { showPopup = false })
    }
}