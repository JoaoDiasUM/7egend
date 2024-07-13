package com.example.myapplication.presentation.messaging

import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.components.MessageContents

@Composable
fun MessagingScreen(
    viewModel: MessagingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    MessageContents()
}