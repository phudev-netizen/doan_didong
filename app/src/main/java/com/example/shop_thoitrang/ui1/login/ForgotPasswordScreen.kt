package com.example.shop_thoitrang.ui1.login

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shop_thoitrang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navHostController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    // Snackbar state for showing the message
    val snackbarHostState = remember { SnackbarHostState() }

    // Handle the message to display
    LaunchedEffect(showMessage) {
        if (showMessage) {
            snackbarHostState.showSnackbar(message)
            showMessage = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Quên Mật Khẩu") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_brightened),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(50))
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 80.dp)
            )

            // Email input field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Nhập Email Đăng Kí") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            // Button for sending reset request
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {
                    // Check if email is not empty and valid
                    if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        message = "Mật Khẩu đã được gửi đến quý khách qua email $email"
                        showMessage = true
                    } else {
                        message = "Vui lòng nhập đúng định dạng email"
                        showMessage = true
                    }
                },
                shape = RoundedCornerShape(50)
            ) {
                Text(text = "Gửi Yêu Cầu", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}
