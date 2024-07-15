package com.example.myapplication.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R


@Composable
fun PopupBox(url: String, showPopup: Boolean, onClickOutside: () -> Unit) {
    if (showPopup) {
        Box(
            modifier = Modifier
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                onDismissRequest = { onClickOutside() }
            ) {
                Box(
                    Modifier
                        .padding(32.dp)
                        .align(Alignment.Center)
                        .width(600.dp)
                        .height(600.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(
                            LocalContext.current
                        ).data(url).crossfade(true).build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_launcher_background)
                    )
                }
            }
        }
    }
}