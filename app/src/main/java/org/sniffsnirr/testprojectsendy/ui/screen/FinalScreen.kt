package org.sniffsnirr.testprojectsendy.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.sniffsnirr.testprojectsendy.R


@SuppressLint("SuspiciousIndentation")
@Composable

fun FinalScreen() {

    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.money)
    )

       Column(
        modifier = Modifier.fillMaxSize().background(color=Color(0xFFD573A0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text= stringResource(R.string.final_screen_label),fontSize = 22.sp,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp,bottom=12.dp),
            textAlign = TextAlign.Center
        )

           LottieAnimation(
               modifier = Modifier.size(300.dp),
               composition = composition.value,
               iterations = LottieConstants.IterateForever,
           )

    }
}