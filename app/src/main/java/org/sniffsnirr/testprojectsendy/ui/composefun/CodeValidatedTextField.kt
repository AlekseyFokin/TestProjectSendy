package org.sniffsnirr.testprojectsendy.ui.composefun

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sniffsnirr.testprojectsendy.R
import org.sniffsnirr.testprojectsendy.ui.composefun.validationstate.ValidationSMSCode
import org.sniffsnirr.testprojectsendy.ui.composefun.validationstate.ValidationSMSCode.Companion.REGEX_CODE_PATTERN

@Composable
fun CodeValidatedTextField(
    code: MutableState<String>,
    isError: MutableState<Boolean>,
    isLoading: State<Boolean>
) {
    val errorMessage: MutableState<ValidationSMSCode> =
        remember { mutableStateOf(ValidationSMSCode.IsEmpty) }

    OutlinedTextField(
        value = code.value,
        textStyle = TextStyle(fontSize=25.sp),
        colors = textFieldColors(),
        placeholder = {Text(text=stringResource(R.string.sms_screen_validated_text_field_code_placeholder))},
        onValueChange = {
            when {
                validateEmptyCode(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationSMSCode.IsEmpty
                    code.value = it
                }

                validateOverHeadCode(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationSMSCode.SMSCodeOverHead
                }

                validateCode(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationSMSCode.NoSMSCode
                    code.value = it
                }

                else -> {
                    isError.value = false
                    errorMessage.value = ValidationSMSCode.NoError
                    code.value = it
                }
            }
        },
        label = { Text(stringResource(R.string.sms_screen_validated_text_field_code_label), color = Color.Black) },
        isError = isError.value,
        supportingText = {
            if (isError.value) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage.value.message?.let { stringResource(it) } ?: "",
                    color = Color.Black
                )
            }
        },
        trailingIcon = {
            if (isError.value)
                Icon(Icons.Filled.Warning, "error", tint = colorScheme.error)
        },
        keyboardActions = KeyboardActions { validateEmptyCode(code.value) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        maxLines = 1,
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .width(250.dp),
        enabled = !isLoading.value
    )
}

fun validateCode(text: String): Boolean {
    val regex = Regex(REGEX_CODE_PATTERN)
    return !regex.matches(text)
}

fun validateEmptyCode(text: String): Boolean {
    return text.isEmpty()
}

fun validateOverHeadCode(text: String): Boolean {
    return text.length>6
}



