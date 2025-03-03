package org.sniffsnirr.testprojectsendy.ui.composefun

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.sniffsnirr.testprojectsendy.R
import org.sniffsnirr.testprojectsendy.ui.composefun.validationstate.ValidationFoneNumber


@Composable
fun ValidatedTextField(
    foneNumber: MutableState<String>,
    isError: MutableState<Boolean>
) {
    val errorMessage: MutableState<ValidationFoneNumber> =
        remember { mutableStateOf(ValidationFoneNumber.NoError) }

    OutlinedTextField(
        value = foneNumber.value,
        onValueChange = {
            when {
                validateEmptyFoneNumber(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationFoneNumber.IsEmpty
                    foneNumber.value = ""
                }

                validateFoneNumberField(it) -> {
                    isError.value = true
                    errorMessage.value = ValidationFoneNumber.NoFoneNumber
                    foneNumber.value = it
                }

                else -> {
                    isError.value = false
                    errorMessage.value = ValidationFoneNumber.NoError
                    foneNumber.value = it
                }
            }
        },
        label = { Text(stringResource(R.string.validated_text_field_fone_number_label)) },
        isError = isError.value,
        supportingText = {
            if (isError.value) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage.value.message ?: "",
                    color = MaterialTheme.colorScheme.error
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

fun validateFoneNumberField(foneNumber: String): Boolean {
    val regex = Regex("^\\+7\\d{10}$")
    return regex.matches(foneNumber)
}

fun validateEmptyFoneNumber(foneNumber: String): Boolean {
    return foneNumber.isEmpty()
}