package com.adrian.practica22.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.adrian.practica22.R
import com.adrian.practica22.R.array.com_google_android_gms_fonts_certs

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Rubik")

val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)