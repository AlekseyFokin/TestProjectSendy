package org.sniffsnirr.testprojectsendy.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.pfe.response.AuthActivateRs
import land.sendy.pfe_sdk.model.pfe.response.BResponse
import land.sendy.pfe_sdk.model.types.ApiCallback
import land.sendy.pfe_sdk.model.types.LoaderError
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.LOG_TAG_NAME
import org.sniffsnirr.testprojectsendy.MainActivity.Companion.SERVER_URL

class SMSViewModel : ViewModel() {

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

    private val _gotoFinalScreen = MutableStateFlow(false)//Все отлично, пора дальше
    val gotoFinalScreen = _gotoFinalScreen.asStateFlow()
    fun resetGotoFinalScreen() {
        _gotoFinalScreen.value = false
    }

    fun confirmation(code: String, context: Context, tokenType: String = "") {
        _isLoading.value = true
        API.outLog("Тест: WS. Попытка активации кошелька ,  $tokenType : $code")
        val runResult: LoaderError? =
            api.activateWlletWS(context, code, tokenType, object : ApiCallback() {
                override fun <T : BResponse?> onSuccess(data: T) {
                    if (data != null) {
                        if (this.getErrNo() == 0) {
                            val response = this.oResponse as AuthActivateRs

                            API.outLog("!!!Response.Active: $response")
                            response.PANs?.let { API.outLog("!!!response.PANs.size=${response.PANs.size}") }
                            if (response.TwoFactor != null && response.TwoFactor && API.checkString(
                                    response.Email
                                )
                            ) {
                                API.outLog("Введите код активации из EMAIL ${response.Email}")
                            } else if (response.Active != null) {
                                // Устанавливаем флаг активации девайса,
                                // вызывая метод из супер класса MasterActivity
                                //activate();
                                API.outLog("Девайс ,буду активировать!")
                                api.acivateDevice(context)
                                // Иной вариант устанавки флага активации девайса:
                                // api.acivateDevice(getApplicationContext());
                                _isLoading.value = false
                                _gotoFinalScreen.value = true
                                API.outLog("Девайс активирован!")
                            } else {
                                API.outLog("Сервер вернул ошибку; $this")
                                API.outLog("Ошибка: $this")
                                _responseError.value = true
                                _errorMessage.value = this.toString()
                                _isLoading.value = false
                            }
                        } else {
                            API.outLog("Сервер вернул ошибку; $this")
                            API.outLog("Ошибка: $this")
                            _responseError.value = true
                            _errorMessage.value = this.toString()
                            _isLoading.value = false
                        }
                    } else {
                        API.outLog("onSuccess. Проблема: сервер не вернул данные!")
                        _connectionError.value = true
                        _errorMessage.value = error.toString()
                        _isLoading.value = false
                    }
                }

                override fun onFail(error: LoaderError?) {
                    API.outLog("Фатальная ошибка: ${error.toString()}")
                    _connectionError.value = true
                    _errorMessage.value = error.toString()
                    _isLoading.value = false
                }
            })

        if (runResult != null && runResult.hasError()) {
            API.outLog("Запрос не был запущен:\r\n$runResult")
            _connectionError.value = true
            _errorMessage.value = runResult.toString()
            _isLoading.value = false
        }
    }
}