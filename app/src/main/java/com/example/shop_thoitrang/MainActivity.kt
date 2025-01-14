package com.example.shop_thoitrang


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shop_thoitrang.ui.theme.Shop_thoitrangTheme
import com.example.shop_thoitrang.ui1.MainScreen

import com.example.shop_thoitrang.ui1.home.HomePage
import com.example.shop_thoitrang.ui1.profile.ProfilePage
import kotlinx.coroutines.MainScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Shop_thoitrangTheme {
                Surface(color = MaterialTheme.colorScheme.background){
                    //HomePage()
                    //CartPage()
                    MainScreen()

                }
            }
        }
    }
}
