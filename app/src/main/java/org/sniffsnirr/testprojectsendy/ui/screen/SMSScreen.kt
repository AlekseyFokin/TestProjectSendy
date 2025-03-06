package org.sniffsnirr.testprojectsendy.ui.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sniffsnirr.testprojectsendy.R
import org.sniffsnirr.testprojectsendy.ui.composefun.CodeValidatedTextField

@Composable
fun SMSScreen(
    nextScreen: () -> Unit,
    vm: SMSViewModel = viewModel()
) {

    val isError = remember { mutableStateOf(true) }// валидация данных для запроса
    val code = remember { mutableStateOf("") }

    val isLoading = vm.isLoading.collectAsState()// состояние ожидания ответа
    val connectionError = vm.connectionError.collectAsState()// состояние ошибки соединения
    val responseError = vm.responseError.collectAsState()// состояние ошибки в ответе
    val errorMessage = vm.errorMessage.collectAsState()// текст ошибки в ответе
    val gotoSMSScreen = vm.gotoFinalScreen.collectAsState()//переход на следующий экран

    val context = LocalContext.current

    Box()
    {
        Column(
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
                            text = stringResource(R.string.sms_screen_label),
                            fontSize = 22.sp,
                            modifier = Modifier
                                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                                .align(Alignment.TopCenter),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    CodeValidatedTextField(code, isError, isLoading)

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        onClick = {
                            vm.confirmation(code.value, context)
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
                            text = stringResource(R.string.sms_screen_button_label),
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
        AlertDialogScreen(
            errorMessage.value,
            stringResource(R.string.alert_dialog_title_connection_error),
            { vm.resetConnectionError() },
            { vm.resetErrorMessage() })
    }

    if (responseError.value) {//ошибка в запросе
        AlertDialogScreen(
            errorMessage.value,
            stringResource(R.string.alert_dialog_title_response_error),
            { vm.resetResponseError() },
            { vm.resetErrorMessage() })
    }

    if (gotoSMSScreen.value) {
        vm.resetGotoFinalScreen()
        nextScreen()
    }
}