package org.sniffsnirr.testprojectsendy.ui.screen

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sniffsnirr.testprojectsendy.R


@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable

fun SplashScreen(gotoLoginScreen: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.splash_animated_vector)
    val atEnd = remember{ mutableStateOf(false)}
       Column(
        modifier = Modifier.fillMaxSize().background(color=MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

           Image(
            modifier = Modifier.size(150.dp),
            painter = rememberAnimatedVectorPainter(image, atEnd.value),
            contentDescription = "",

        )

           LaunchedEffect(key1 = true) {
               coroutineScope.launch {
                   delay(3000)
                   atEnd.value=true
                   gotoLoginScreen()
               }
           }
    }
}