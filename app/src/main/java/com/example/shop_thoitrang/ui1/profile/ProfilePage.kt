package com.example.shop_thoitrang.ui1.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shop_thoitrang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECEFF1))
    ) {
        // Header
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Profile Picture
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFBBDEFB), shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            },
            actions = {
                Row {
                    TextButton(onClick = { navController.navigate("login") }) {
                        Text("Đăng nhập", color = Color.Cyan)
                    }
                    TextButton(onClick = { navController.navigate("register") }) {
                        Text("Đăng ký", color = Color.Cyan)
                    }
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFF4C2C2))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Orders Section
        Text(
            text = "Đơn hàng của tôi",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE)),
            modifier = Modifier.padding(start = 16.dp)
        )

        // Order Options
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OrderOption(text = "Chờ thanh toán", icon = Icons.Default.AttachMoney)
            OrderOption(text = "Chờ vận chuyển", icon = Icons.Default.LocalShipping)
            OrderOption(text = "Chờ giao hàng", icon = Icons.Default.Assignment)
            OrderOption(text = "Chưa đánh giá", icon = Icons.Default.Star)
            OrderOption(text = "Đổi trả & Hủy", icon = Icons.Default.Cancel)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Section
        Text(
            text = "Cài đặt",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE)),
            modifier = Modifier.padding(start = 16.dp)
        )

        // Settings Options
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(2f) // Makes the Column fill available space
        ) {
            SettingOption(text = "My Profile", icon = Icons.Default.Person, onClick = { navController.navigate("edit_profile") })
            Spacer(modifier = Modifier.height(8.dp))
            SettingOption(text = "Delivery Address", icon = Icons.Default.LocationOn, onClick = { navController.navigate("delivery_address") })
            Spacer(modifier = Modifier.height(8.dp))
            SettingOption(text = "Payment Methods", icon = Icons.Default.Payment, onClick = { navController.navigate("payment_methods") })
            Spacer(modifier = Modifier.height(8.dp))
            SettingOption(text = "Password Setting", icon = Icons.Default.Lock, onClick = { navController.navigate("password_setting") })
            Spacer(modifier = Modifier.height(8.dp))
            SettingOption(text = "Log Out", icon = Icons.Default.ExitToApp, onClick = { navController.navigate("logout") })
        }

        // Footer will always be at the bottom
        Footer(navController)
    }
}

@Composable
fun SettingOption(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color(0xFF6200EE))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = TextStyle(fontSize = 16.sp, color = Color.Black))
    }
}

@Composable
fun SettingOption(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4C2C2))
    ) {
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun OrderOption(text: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp)) // Rounded corners
            .padding(8.dp)
            .width(60.dp) // Fixed width for even spacing
            .height(80.dp) // Fixed height for consistent option size
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color(0xFF6200EE)) // Purple tint
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center, color = Color(0xFF333333)))
    }
}

@Composable
fun Footer(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "Home",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("home")
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_category),
            contentDescription = "Product",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("products")
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_favorite_outlined),
            contentDescription = "Favorite",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("favorite")
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("profile")
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "Logout",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("login")
                }
        )
    }
}
