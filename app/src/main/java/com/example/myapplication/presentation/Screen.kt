package com.example.myapplication.presentation

sealed class Screen(val route: String) {
    data object MessagingScreen : Screen("messaging_screen")
}