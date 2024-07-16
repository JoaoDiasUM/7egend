package com.example.myapplication.feature_chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.feature_chat.domain.model.User

@Composable
fun UserItem(navController: NavController, user: User, exampleMessage: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(10.dp)
            .clickable {
                navController.navigate("user_chat_screen/${user.id}")
            }
    ) {
        Row {
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

            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = user.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                exampleMessage?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        text = it,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {

}
