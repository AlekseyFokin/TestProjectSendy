package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

import org.sniffsnirr.testprojectsendy.R

sealed class ValidationSMSCode(var id: Int, var message: Int?) {
    object NoError : ValidationSMSCode(id = 0, message = null)
    object NoSMSCode : ValidationSMSCode(id = 1, message = R.string.sms_screen_text_field_error_no_code)
    object IsEmpty : ValidationSMSCode(id = 2, message = R.string.sms_screen_text_field_error_code_empty)
    object SMSCodeOverHead : ValidationSMSCode(id =3, message = R.string.sms_screen_text_field_error_overhead_number)

    companion object
    const val REGEX_CODE_PATTERN="^\\d{6}$"
}