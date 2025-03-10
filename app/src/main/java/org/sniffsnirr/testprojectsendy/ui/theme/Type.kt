package org.sniffsnirr.testprojectsendy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sniffsnirr.testprojectsendy.R

// Set of Material typography styles to start with

val MyCustomFont = FontFamily(
    Font(R.font.philosopher_bold),
    Font(R.font.philosopher_bold,FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
    //    fontFamily = FontFamily.Default,
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp),

        titleLarge = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),


)