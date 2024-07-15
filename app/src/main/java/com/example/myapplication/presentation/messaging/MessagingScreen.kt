package com.example.myapplication.presentation.messaging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.presentation.components.UserItem

@Composable
fun MessagingScreen(
    navController: NavController,
    viewModel: MessagingViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val userList = state.value.users

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 30.dp, 30.dp, 0.dp)
                .background(Color.DarkGray),
        ) {
            Text(
                modifier = Modifier
                    .padding(30.dp),
                text = "Ervin Howell",
                color = Color.White
            )

            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data("https://picsum.photos/200/300").crossfade(true).build(),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )
        }


        if(state.value.users?.isNotEmpty() == true) {
            LazyColumn(
                Modifier
                    .padding(30.dp, 10.dp, 30.dp, 30.dp)
                    .weight(0.6f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true
            ) {
                userList?.forEach { user ->
                    item {
                        val exampleMessage = state.value.messages?.last {
                            it.userId == user.id
                        }

                        UserItem(
                            user = user,
                            exampleMessage = exampleMessage?.content,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}