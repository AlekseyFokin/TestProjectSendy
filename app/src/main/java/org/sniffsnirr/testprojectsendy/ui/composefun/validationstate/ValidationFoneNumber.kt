package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

import org.sniffsnirr.testprojectsendy.R

sealed class ValidationFoneNumber( var message: Int?) {
    object NoError : ValidationFoneNumber( message = null)
    object NoFoneNumber : ValidationFoneNumber( message = R.string.login_screen_text_field_error_no_fone_number)
    object IsEmpty : ValidationFoneNumber( message = R.string.login_screen_text_field_error_is_empty)
    object OverHeadNumber : ValidationFoneNumber( message = R.string.login_screen_text_field_error_overhead_number)

    companion object{
        const val REGEX_FONE_NUMBER_PATTERN="^\\+7\\d{10}$"
    }
}