package org.sniffsnirr.testprojectsendy.ui.composefun.validationstate

sealed class ValidationFoneNumber(var id: Int, var message: String?) {
    object NoError : ValidationFoneNumber(id = 0, message = null)
    object NoFoneNumber : ValidationFoneNumber(id = 1, message = "номер телефона должен быть в формате +7XXXXXXXXXX")
    object IsEmpty : ValidationFoneNumber(id = 2, message = "номер телефона не должен быть пустым")
}