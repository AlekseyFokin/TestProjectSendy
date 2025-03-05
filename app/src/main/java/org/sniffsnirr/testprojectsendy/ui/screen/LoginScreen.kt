package org.sniffsnirr.testprojectsendy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.testprojectsendy.R
import org.sniffsnirr.testprojectsendy.ui.composefun.FoneValidatedTextField

@Composable
fun LoginScreen(
    onLoginClick: (
        foneNumber: MutableState<String>,
        isLoading: MutableState<Boolean>,
        connectionError: MutableState<Boolean>,
        responseError: MutableState<Boolean>,
        errorMessage: MutableState<String>
    ) -> Unit
) {

    val isError = remember { mutableStateOf(true) }// валидация данных для запроса
    val foneNumber = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(false) }
    //val isLoading = vm.isLoading.collectAsState()// состояние ожидания ответа

    val connectionError = remember { mutableStateOf(false) }// состояние ошибки соединения
    val responseError = remember { mutableStateOf(false) }// состояние ошибки в ответе
    val errorMessage = remember { mutableStateOf("") }// текст ошибки в ответе

    val context = LocalContext.current

Box()
    { Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD573A0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    color = Color(0xFFF3A3B4),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(3.dp, Color.Yellow, shape = RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
                        text = stringResource(R.string.login_screen_label),
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                            .align(Alignment.TopCenter),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                FoneValidatedTextField(foneNumber, isError,isLoading)

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        onLoginClick(foneNumber, isLoading, connectionError, responseError,errorMessage)
                        Toast.makeText(
                            context,
                            "foneNumber: ${foneNumber.value}",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    modifier = Modifier.padding(bottom = 28.dp),
                    enabled = !(isError.value || isLoading.value),
                    colors = ButtonColors(
                        containerColor = Color.Yellow,
                        disabledContainerColor = Color.Gray,
                        contentColor = Color.Black,
                        disabledContentColor = Color.Black
                    )

                ) {
                    Text(
                        text = stringResource(R.string.login_screen_button_label),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                        )
                    )
                }
            }
        }
    }

    if (isLoading.value) {// прогрессбар
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .background(color = Color(0x85CD9DF3), shape = RectangleShape),
        )
        CircularProgressIndicator(
            color = Color.Magenta,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

    if (connectionError.value) {// ошибка соединения
        AlertDialog(
            modifier = Modifier.border(2.dp, Color.Red),
            shape = RectangleShape,
            onDismissRequest = {
                connectionError.value = false
                errorMessage.value = ""
            },
            title = { Text(text = stringResource(R.string.alert_dialog_title_connection_error)) },
            text = { Text(errorMessage.value) },
            confirmButton = {
                Button({
                    connectionError.value = false
                    errorMessage.value = ""
                }) {
                    Text(stringResource(R.string.button_ok_label), fontSize = 22.sp)
                }
            }
        )
    }

    if (responseError.value) {
        AlertDialog(
            modifier = Modifier.border(2.dp, Color.Red),
            shape = RectangleShape,
            onDismissRequest = {
                responseError.value = false
                errorMessage.value = ""
            },
            title = { Text(text = stringResource(R.string.alert_dialog_title_response_error)) },
            text = { Text(errorMessage.value) },
            confirmButton = {
                Button({
                    responseError.value = false
                    errorMessage.value = ""
                }) {
                    Text(stringResource(R.string.button_ok_label), fontSize = 22.sp)
                }
            }
        )
    }


}