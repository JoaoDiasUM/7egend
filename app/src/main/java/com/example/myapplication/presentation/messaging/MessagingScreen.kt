package com.example.myapplication.presentation.messaging

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MessagingScreen(
    viewModel: MessagingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
}