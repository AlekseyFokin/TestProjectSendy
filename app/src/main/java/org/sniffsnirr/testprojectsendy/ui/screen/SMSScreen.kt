package org.sniffsnirr.testprojectsendy.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.sniffsnirr.testprojectsendy.ui.composefun.ValidatedTextField

@Composable
fun SMSScreen(){
val isError = remember { mutableStateOf(false) }// валидация данных для запроса
val context = LocalContext.current
val foneNumber= remember { mutableStateOf("") }
val isLoading = remember { mutableStateOf(false) }

val clickStartRequestButton: () -> Unit = {}// vm.setRequestObject() }

Column(modifier = Modifier.fillMaxSize(),
horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
) {
    Text(text="Для входа в приложение введите номер телефона")

//    ValidatedTextField(foneNumber,isError)

    IconButton(
        onClick = {
            //   onClickButton()
            Toast.makeText(
                context,
                "This is URL: ${foneNumber.value}",
                Toast.LENGTH_LONG
            ).show()
        },
        modifier = Modifier
            .size(40.dp)
            .padding(PaddingValues(bottom = 10.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(40.dp)
            ),

        enabled = !(isError.value || isLoading.value),

        ) {
        Text("Продолжить")
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "content description",
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.fillMaxSize()
        )
    }

}
}