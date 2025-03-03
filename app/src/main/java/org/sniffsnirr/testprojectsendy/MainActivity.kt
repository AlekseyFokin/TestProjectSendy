package org.sniffsnirr.testprojectsendy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sniffsnirr.testprojectsendy.ui.navigation.Login
import org.sniffsnirr.testprojectsendy.ui.navigation.Splash
import org.sniffsnirr.testprojectsendy.ui.screen.LoginScreen
import org.sniffsnirr.testprojectsendy.ui.screen.SplashScreen
import org.sniffsnirr.testprojectsendy.ui.theme.TestProjectSendyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestProjectSendyTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Splash) {
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
                        LoginScreen {}
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestProjectSendyTheme {
        Greeting("Android")
    }
}