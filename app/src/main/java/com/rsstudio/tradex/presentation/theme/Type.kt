package com.rsstudio.tradex.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Typography.subTitle: TextStyle
    get() = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        color = slateGray
    )

val Typography.captionDefault: TextStyle
    get() = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
        letterSpacing = 0.3.sp,
        color = lightGray,
        lineHeight = 20.sp,
    )

val Typography.captionBold: TextStyle
    get() = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        color = charcoal,
    )

val Typography.ParagraphSmallRegular: TextStyle
    get() = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.3.sp,
    )
