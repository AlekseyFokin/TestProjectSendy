package org.sniffsnirr.testprojectsendy.ui.screen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.types.ApiCallback
import land.sendy.pfe_sdk.model.types.LoaderError
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.LOG_TAG_NAME
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.SERVER_URL
import org.sniffsnirr.testprojectsendy.ui.navigation.SMS

class LoginViewModel: ViewModel() {

    val api = API.getInsatce(SERVER_URL, LOG_TAG_NAME)

    private val _connectionError =MutableStateFlow(false)// ошибка доступа
    val connectionError = _connectionError.asStateFlow()

    private val _responseError =MutableStateFlow(false)// ошибка доступа
    val responseError = _responseError.asStateFlow()

    private val _isLoading =MutableStateFlow(false)// ошибка доступа
    val isLoading = _isLoading.asStateFlow()

    fun loginRequest(
        foneNumber: MutableState<String>,
        isLoading: MutableState<Boolean>,
        connectionError: MutableState<Boolean>,
        responseError: MutableState<Boolean>,
        errorMessage: MutableState<String>
    ) {

        API.outLog("Tест: WS. Попытка старта активации кошелька: $foneNumber")
        isLoading.value = true

        val runResult: LoaderError? =
            api.loginAtAuthWS(this, foneNumber.value, object : ApiCallback() {

                override fun onCompleted(res: Boolean) {
                    if ((!res) || (getErrNo() != 0)) {
                        API.outLog("Ошибка: ${this.toString()}")
                        responseError.value = true
                        errorMessage.value = this.toString()
                    } else {
                        // Обработка результатов запроса. this.oResponse тип AuthLoginRs
                        API.outLog("runResult запрос был запущен:\r\n ${this.oResponse}")
                        navController.navigate(SMS)
                    }
                    isLoading.value = false
                    API.outLog("КОНЕЦ")
                }
            })

        if (runResult != null && runResult.hasError()) {
            API.outLog("runResult запрос не был запущен:\r\n ${runResult.toString()}")
            connectionError.value = true
            errorMessage.value = runResult.toString()
            isLoading.value = false
        }
    }

}