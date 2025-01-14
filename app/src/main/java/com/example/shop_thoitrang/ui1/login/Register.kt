package com.example.shop_thoitrang.ui1.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.shop_thoitrang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navHostController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent // Make background transparent to use the image
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.back), // Replace 'name' with your image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize() // Make the image cover the entire screen
                    .align(Alignment.Center)
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_brightened),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(50))
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 80.dp)
                )
                Text(
                    text = "Đăng Ký Tài Khoản ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Căn giữa
                        .padding(top = 20.dp , bottom = 0.dp) // Reduced top padding
                )
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        placeholder = { Text(text = "Tên người dùng") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp) // Reduced padding here
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                        },
                        placeholder = { Text(text = "Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp), // Reduced padding here
                        isError = emailError,
                    )
                    if (emailError) {
                        Text(
                            text = "Vui lòng nhập email đúng định dạng (name@gmail.com)",
                            color = androidx.compose.ui.graphics.Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 20.dp)
                        )
                    }
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = confirmPassword.isNotEmpty() && confirmPassword != it
                        },
                        placeholder = { Text(text = "Mật khẩu") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp), // Reduced padding here
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            passwordError = it != password
                        },
                        placeholder = { Text(text = "Nhập lại mật khẩu") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp), // Reduced padding here
                        isError = passwordError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    if (passwordError) {
                        Text(
                            text = "Mật khẩu xác nhận không khớp!",
                            color = androidx.compose.ui.graphics.Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 20.dp)
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), // Reduced padding here
                        onClick = {
                            if (!emailError && !passwordError) {
                                /* handle registration */
                                navHostController.navigate("login")
                            }
                        },
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(text = "Đăng Ký", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                    TextButton(onClick = { navHostController.navigate("login") }) {
                        Text(text = "Đã có tài khoản? Đăng nhập")
                    }
                }
            }
        }
    }
}
