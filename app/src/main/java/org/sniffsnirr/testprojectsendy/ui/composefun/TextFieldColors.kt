package org.sniffsnirr.testprojectsendy.ui.composefun

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TextFieldColors() = TextFieldDefaults.colors(
    unfocusedContainerColor = Color.White,
    unfocusedTextColor = Color.Black,
    focusedContainerColor = Color.White,
    focusedTextColor = Color.Black,
    focusedIndicatorColor = Color.Yellow,
    unfocusedIndicatorColor = Color.Yellow
)