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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import org.sniffsnirr.testprojectsendy.ui.composefun.validationstate.ValidationFoneNumber
import org.sniffsnirr.testprojectsendy.ui.composefun.validationstate.ValidationFoneNumber.Companion.REGEX_FONE_NUMBER_PATTERN


@Composable
fun FoneValidatedTextField(
    foneNumber: MutableState<String>,
    isError: MutableState<Boolean>
) {
    val errorMessage: MutableState<ValidationFoneNumber> =
        remember { mutableStateOf(ValidationFoneNumber.IsEmpty) }

    OutlinedTextField(
        value = foneNumber.value,
        textStyle = TextStyle(fontSize=25.sp),
        colors = TextFieldColors(),
        placeholder = {Text(text=stringResource(R.string.login_screen_validated_text_field_fone_number_placeholder))},
        onValueChange = {
            when {
                validateEmptyFoneNumber(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationFoneNumber.IsEmpty
                    foneNumber.value = it
                }

                validateFoneNumber(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationFoneNumber.NoFoneNumber
                    foneNumber.value = it
                }

                validateOverHeadFoneNumber(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationFoneNumber.OverHeadNumber
                }

                else -> {
                    isError.value = false
                    errorMessage.value = ValidationFoneNumber.NoError
                    foneNumber.value = it
                }
            }
        },
        label = { Text(stringResource(R.string.login_screen_validated_text_field_fone_number_label), color = Color.Black) },
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
        keyboardActions = KeyboardActions { validateEmptyFoneNumber(foneNumber.value) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        maxLines = 1,
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .width(250.dp),
    )
}

fun validateFoneNumber(text: String): Boolean {
    val regex = Regex(REGEX_FONE_NUMBER_PATTERN)
    return !regex.matches(text)
}

fun validateEmptyFoneNumber(text: String): Boolean {
    return text.isEmpty()
}

fun validateOverHeadFoneNumber(text: String): Boolean {
    return text.length>12
}

