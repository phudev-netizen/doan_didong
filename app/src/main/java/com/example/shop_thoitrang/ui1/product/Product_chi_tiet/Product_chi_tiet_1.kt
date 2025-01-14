package com.example.shop_thoitrang.ui1.product.Product_chi_tiet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailPage(navController: NavController) {
    var addedToCart by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    // Giả lập danh sách sản phẩm yêu thích
    val favorites = remember { mutableStateListOf<String>() }
    val productId = "product_id" // ID của sản phẩm hiện tại

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Màu nền cho trang
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Ảnh sản phẩm", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                        .size(48.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Trở về", tint = Color.White)
                }
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            favorites.remove(productId) // Xóa khỏi danh sách yêu thích
                        } else {
                            favorites.add(productId) // Thêm vào danh sách yêu thích
                        }
                        isFavorite = !isFavorite // Chuyển đổi trạng thái
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Thêm vào yêu thích",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tên sản phẩm",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Giá tiền: 1,000,000 VND",
                fontSize = 20.sp,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Mô tả sản phẩm:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Đây là mô tả chi tiết về sản phẩm. Sản phẩm được làm từ chất liệu cao cấp, mang lại sự thoải mái và phong cách cho người sử dụng.",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Màu sắc:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ColorBox(Color.Red)
                ColorBox(Color.Green)
                ColorBox(Color.Blue)
                ColorBox(Color.Yellow)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Kích thước:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SizeBox("S")
                SizeBox("M")
                SizeBox("L")
                SizeBox("XL")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            addedToCart = true
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00A79D)
                        )
                    ) {
                        Text(
                            text = if (addedToCart) "Đã thêm vào giỏ hàng" else "Thêm vào giỏ hàng",
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate("cart")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5722)
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Mua với voucher",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                            Text(
                                text = "$$$",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
        if (addedToCart) {
            Snackbar(
                modifier = Modifier
                    .align(Alignment.Center),
                containerColor = Color.Black,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Sản phẩm đã được thêm vào giỏ hàng!")
                }
            }
        }
    }
}

@Composable
fun ColorBox(color: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color, shape = MaterialTheme.shapes.medium),
    )
}

@Composable
fun SizeBox(size: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(size, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
