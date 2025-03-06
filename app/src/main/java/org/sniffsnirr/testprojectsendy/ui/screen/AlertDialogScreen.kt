package org.sniffsnirr.testprojectsendy.ui.screen

import androidx.compose.foundation.border
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.testprojectsendy.R

@Composable
fun AlertDialogScreen(text: String, textType: String, resetError: () -> Unit, resetErrorMessage: () -> Unit) {
    AlertDialog(
        modifier = Modifier.border(2.dp, Color.Red),
        shape = RectangleShape,
        onDismissRequest = {
            resetError()
            resetErrorMessage()
        },
        title = {
            Text(
                text = textType,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
            )
        },
        text = {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
            )
        },
        confirmButton = {
            Button({
                resetError()
                resetErrorMessage()
            }) {
                Text(stringResource(R.string.button_ok_label), style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ))
            }
        }
    )
}