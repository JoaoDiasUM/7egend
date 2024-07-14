package com.example.myapplication.presentation

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
import androidx.room.Room
import com.example.myapplication.data.room.database.AttachmentDatabase
import com.example.myapplication.data.room.database.MessageDatabase
import com.example.myapplication.data.room.database.UserDatabase
import com.example.myapplication.presentation.chat.UserChatScreen
import com.example.myapplication.presentation.messaging.MessagingScreen
import com.example.myapplication.presentation.ui.theme.MessagingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        lateinit var userDatabase: UserDatabase
        lateinit var attachmentDatabase: AttachmentDatabase
        lateinit var messageDatabase: MessageDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userDatabase = Room.databaseBuilder(applicationContext,
            UserDatabase::class.java,"user_database").build()

        messageDatabase = Room.databaseBuilder(applicationContext,
            MessageDatabase::class.java,"message_database").build()

        attachmentDatabase = Room.databaseBuilder(applicationContext,
            AttachmentDatabase::class.java,"attachment_database").build()

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
