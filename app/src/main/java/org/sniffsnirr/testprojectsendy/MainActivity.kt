package org.sniffsnirr.testprojectsendy

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import land.sendy.pfe_sdk.activies.MasterActivity
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.pfe.interfaces.IDataLoader
import land.sendy.pfe_sdk.model.pfe.response.AuthLoginRs
import land.sendy.pfe_sdk.model.types.ApiCallback
import land.sendy.pfe_sdk.model.types.LoaderError
import org.sniffsnirr.testprojectsendy.ui.navigation.Final
import org.sniffsnirr.testprojectsendy.ui.navigation.Login
import org.sniffsnirr.testprojectsendy.ui.navigation.SMS
import org.sniffsnirr.testprojectsendy.ui.navigation.Splash
import org.sniffsnirr.testprojectsendy.ui.screen.FinalScreen
import org.sniffsnirr.testprojectsendy.ui.screen.LoginScreen
import org.sniffsnirr.testprojectsendy.ui.screen.SMSScreen
import org.sniffsnirr.testprojectsendy.ui.screen.SplashScreen
import org.sniffsnirr.testprojectsendy.ui.theme.TestProjectSendyTheme


class MainActivity : MasterActivity() {

    lateinit var navController: NavController
    val api = API.getInsatce(SERVER_URL, LOG_TAG_NAME)
    lateinit var lp: Looper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        // api = API.getInsatce(SERVER_URL, LOG_TAG_NAME)
        //lp = Looper.getMainLooper()

        enableEdgeToEdge()
        setContent {
            TestProjectSendyTheme {

                navController = rememberNavController()
                NavHost(
                    navController = navController as NavHostController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen {
                            navController.navigate(Login) {
                                popUpTo(Splash) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    composable<Login> {
                        LoginScreen(::loginRequest)
                    }

                    composable<SMS> {
                        SMSScreen { navController.navigate(Final) }
                    }

                    composable<Final> {
                        FinalScreen()
                    }
                }
            }
        }
    }


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

    companion object {
        const val SERVER_URL = "https://testwallet.sendy.land"
        const val LOG_TAG_NAME = "SENDY"
        const val WHITE_LABEL = 1
    }
}
