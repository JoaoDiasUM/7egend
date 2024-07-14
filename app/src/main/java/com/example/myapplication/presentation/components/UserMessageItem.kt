package com.example.myapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.data.remote.dto.Attachment
import com.example.myapplication.data.remote.dto.Message
import com.example.myapplication.presentation.ui.theme.PurpleGrey40
import com.example.myapplication.presentation.ui.theme.PurpleGrey80

@Composable
fun UserMessageItem(message: Message) {

    val isFromMe = message.isFromMe

    val alignment = if (message.isFromMe) Alignment.End else Alignment.Start

    val messageColor = if (isFromMe) Color.Blue else Color.Gray

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
                    ).data(attachment.thumbnailUrl).crossfade(true).build(),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(10.dp)
                        .fillMaxWidth()
                        .align(alignment),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_launcher_background)
                )
            }
        }
    }
}

@Preview
@Composable
fun UserMessageItemPreview() {

    val attachment =
        Attachment("1", "https://picsum.photos/200/300", "", "https://picsum.photos/200/300")

    val message = Message(listOf(attachment), "test message", 1, 1)

    val message2 = Message(listOf(attachment, attachment), "message testsahduba", 1, 2)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            UserMessageItem(message = message)

            UserMessageItem(message = message2)
        }
    }
}