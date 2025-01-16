package com.rsstudio.tradex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rsstudio.tradex.presentation.home.HomeScreen
import com.rsstudio.tradex.presentation.theme.TradeXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradeXTheme {
                HomeScreen()
            }
        }
    }
}