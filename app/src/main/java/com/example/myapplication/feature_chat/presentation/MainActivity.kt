package com.example.myapplication.feature_chat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.feature_chat.presentation.chat.UserChatScreen
import com.example.myapplication.feature_chat.presentation.messaging.MessagingScreen
import com.example.myapplication.feature_chat.presentation.ui.theme.MessagingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MessagingAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MessagingScreen.route
                    ) {
                        composable(
                            route = Screen.MessagingScreen.route
                        ) {
                            MessagingScreen(navController)
                        }

                        composable(
                            route = Screen.UserChatScreen.route,
                            arguments = listOf(navArgument("userId") { defaultValue = "user1234" })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getString("userId", "user1234")?.toInt()
                                ?.let { UserChatScreen(it) }
                        }
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MessagingAppTheme {

        }
    }
}
