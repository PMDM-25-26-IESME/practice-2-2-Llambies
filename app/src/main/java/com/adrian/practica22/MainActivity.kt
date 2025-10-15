package com.adrian.practica22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.adrian.practica22.ui.VatScreen
import com.adrian.practica22.ui.theme.Practica22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica22Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VatScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
