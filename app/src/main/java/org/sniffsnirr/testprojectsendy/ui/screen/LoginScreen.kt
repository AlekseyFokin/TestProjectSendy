package org.sniffsnirr.testprojectsendy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.testprojectsendy.ui.composefun.ValidatedTextField

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {

    val isError = remember { mutableStateOf(false) }// валидация данных для запроса
    val context = LocalContext.current
    val foneNumber = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }

    val clickStartRequestButton: () -> Unit = {}// vm.setRequestObject() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .height(350.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(3.dp, Color.Yellow),
            //contentAlignment = Alignment.Center
            contentAlignment = Alignment.TopCenter
        ) {
            Column(verticalArrangement = Arrangement.Top) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                        .background(
                            color = Color.Yellow,
                            shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                        )
                        .border(3.dp, Color.Yellow),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Для входа в приложение введите номер телефона",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                            .align(Alignment.Center)
                    )
                }

                ValidatedTextField(foneNumber, isError)

                Button(
                    onClick = {
                        //   onClickButton()
                        Toast.makeText(
                            context,
                            "foneNumber: ${foneNumber.value}",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier,
                    enabled = !(isError.value || isLoading.value),
                    colors = ButtonColors(
                        containerColor = Color.Yellow,
                        disabledContainerColor = Color.Gray,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Black
                    )

                ) {
                    Text(
                        text = "Продолжить",
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}