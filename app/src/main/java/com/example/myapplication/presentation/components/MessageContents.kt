package com.example.myapplication.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageContents() {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Column(modifier = Modifier.fillMaxSize()){
            OutlinedTextField(
                modifier = Modifier.align(Alignment.End).size(50.dp),
                value = "12",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                readOnly = true,
            )

            OutlinedTextField(
                modifier = Modifier.align(Alignment.Start).size(50.dp),
                value = "20",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                readOnly = true,
            )
        }
    }
}