package org.sniffsnirr.testprojectsendy.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.types.ApiCallback
import land.sendy.pfe_sdk.model.types.LoaderError
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.LOG_TAG_NAME
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.SERVER_URL

class LoginViewModel : ViewModel() {

    val api = API.getInsatce(SERVER_URL, LOG_TAG_NAME)

    private val _connectionError = MutableStateFlow(false)// ошибка доступа
    val connectionError = _connectionError.asStateFlow()
    fun resetConnectionError() {
        _connectionError.value = !_connectionError.value
    }

    private val _responseError = MutableStateFlow(false)// ошибка запроса
    val responseError = _responseError.asStateFlow()
    fun resetResponseError() {
        _responseError.value = !_responseError.value
    }

    private val _isLoading = MutableStateFlow(false)// состояние загрузки
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")// состояние загрузки
    val errorMessage = _errorMessage.asStateFlow()
    fun resetErrorMessage() {
        _errorMessage.value = ""
    }

    private val _gotoSMSScreen = MutableStateFlow(false)//Все отлично, пора дальше
    val gotoSMSScreen = _gotoSMSScreen.asStateFlow()
    fun resetGotoSMSScreen() {
        _gotoSMSScreen.value = false
    }


    fun loginRequest(foneNumber: String, context: Context) {

        API.outLog("Tест: WS. Попытка старта активации кошелька: $foneNumber")
        _isLoading.value = true

        val runResult: LoaderError? =
            api.loginAtAuthWS(context, foneNumber, object : ApiCallback() {

                override fun onCompleted(res: Boolean) {
                    if ((!res) || (getErrNo() != 0)) {
                        API.outLog("Ошибка: $this")
                        _responseError.value = true
                        _errorMessage.value = this.toString()
                    } else {
                        // Обработка результатов запроса. this.oResponse тип AuthLoginRs
                        API.outLog("runResult запрос был запущен:\r\n ${this.oResponse}")
                        _gotoSMSScreen.value = true
                    }
                    _isLoading.value = false
                    API.outLog("КОНЕЦ")
                }
            })

        if (runResult != null && runResult.hasError()) {
            API.outLog("runResult запрос не был запущен:\r\n $runResult")
            _connectionError.value = true
            _errorMessage.value = runResult.toString()
            _isLoading.value = false
        }
    }
}
