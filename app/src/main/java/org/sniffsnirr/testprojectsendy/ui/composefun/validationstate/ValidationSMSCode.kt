package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

sealed class ValidationSMSCode(var id: Int, var message: String?) {
    object NoError : ValidationSMSCode(id = 0, message = null)
    object NoSMSCode : ValidationSMSCode(id = 1, message = "код из СМС - 6 символов")
    object IsEmpty : ValidationSMSCode(id = 2, message = "код из СМС должен быть введен")
}