package org.sniffsnirr.testprojectsendy

import android.os.Bundle
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import land.sendy.pfe_sdk.activies.MasterActivity
import land.sendy.pfe_sdk.api.API
import org.sniffsnirr.testprojectsendy.ui.navigation.Final
import org.sniffsnirr.testprojectsendy.ui.navigation.Login
import org.sniffsnirr.testprojectsendy.ui.navigation.SMS
import org.sniffsnirr.testprojectsendy.ui.screen.FinalScreen
import org.sniffsnirr.testprojectsendy.ui.screen.LoginScreen
import org.sniffsnirr.testprojectsendy.ui.screen.SMSScreen

import org.sniffsnirr.testprojectsendy.ui.theme.TestProjectSendyTheme

class MainActivity : MasterActivity() {

    lateinit var navController: NavController
    val api = API.getInsatce(SERVER_URL, LOG_TAG_NAME)
    lateinit var lp: Looper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportActionBar?.hide()

        lp = Looper.getMainLooper()

        enableEdgeToEdge()
        setContent {
            TestProjectSendyTheme {

                navController = rememberNavController()
                NavHost(
                    navController = navController as NavHostController,
                    startDestination = Login
                ) {
                    composable<Login> {
                        LoginScreen({navController.navigate(SMS)})
                    }

                    composable<SMS> {
                        SMSScreen( { navController.navigate(Final) })
                    }

                    composable<Final> {
                        FinalScreen()
                    }
                }
            }
        }
    }

    companion object {
        const val SERVER_URL = "https://testwallet.sendy.land"
        const val LOG_TAG_NAME = "SENDY"
        const val WHITE_LABEL = 1
    }
}
