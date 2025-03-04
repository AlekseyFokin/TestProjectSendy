package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

import org.sniffsnirr.testprojectsendy.R

sealed class ValidationFoneNumber(var id: Int, var message: Int?) {
    object NoError : ValidationFoneNumber(id = 0, message = null)
    object NoFoneNumber : ValidationFoneNumber(id = 1, message = R.string.login_screen_text_field_error_no_fone_number)
    object IsEmpty : ValidationFoneNumber(id = 2, message = R.string.login_screen_text_field_error_is_empty)
    object OverHeadNumber : ValidationFoneNumber(id = 3, message = R.string.login_screen_text_field_error_overhead_number)

    companion object{
        const val REGEX_FONE_NUMBER_PATTERN="^\\+7\\d{10}$"
    }
}