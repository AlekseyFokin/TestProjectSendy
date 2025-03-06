package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

import org.sniffsnirr.testprojectsendy.R

sealed class ValidationSMSCode( var message: Int?) {
    object NoError : ValidationSMSCode (message = null)
    object NoSMSCode : ValidationSMSCode(message = R.string.sms_screen_text_field_error_no_code)
    object IsEmpty : ValidationSMSCode( message = R.string.sms_screen_text_field_error_code_empty)
    object SMSCodeOverHead : ValidationSMSCode( message = R.string.sms_screen_text_field_error_overhead_number)

    companion object{
    const val REGEX_CODE_PATTERN="^\\d{6}$"}
}