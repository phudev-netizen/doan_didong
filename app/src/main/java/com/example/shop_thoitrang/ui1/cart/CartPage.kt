//package com.example.shop_thoitrang.ui1.cart
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.shop_thoitrang.R
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CartPage(navController: NavController) {
//    var cartItems by remember {
//        mutableStateOf(
//            listOf(
//                CartItem(R.drawable.quanje, "Áo khoác nữ mùa đông", "Áo hoodie dày", 4.9, "$57"),
//                CartItem(R.drawable.ao_khoac, "Áo khoác nam mùa đông", "Áo dạ dài", 4.8, "$62"),
//                CartItem(R.drawable.quangin, "Túi xách nữ có bảy túi", "Túi xách thanh lịch", 4.9, "$68"),
//                CartItem(R.drawable.dam, "Đầm nữ dự tiệc", "Đầm dài sang trọng", 4.7, "$75"),
//                CartItem(R.drawable.ao_so_mi, "Áo sơ mi nam", "Sơ mi công sở", 4.6, "$35"),
//                CartItem(R.drawable.ao_thun, "Áo thun nữ", "Chất liệu cotton", 4.8, "$25"),
//                CartItem(R.drawable.damdo, "Quần jeans nữ", "Phong cách trẻ trung", 4.9, "$45"),
//                CartItem(R.drawable.ao, "Váy midi nữ", "Chất liệu voan", 4.8, "$50")
//            )
//        )
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        Column {
//            TopAppBar(
//                title = {
//                    Text("Giỏ hàng", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White)
//            )
//
////            Text(
////                text = "Giỏ hàng",
////                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
////                modifier = Modifier.padding(vertical = 16.dp)
////            )
//
//            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                items(cartItems) { item ->
//                    CartItemCard(item = item, onDelete = {
//                        cartItems = cartItems.filterNot { it == item }
//                    })
//                }
//            }
//        }
//        Button(
//            onClick = { navController.navigate("checkout") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
//        ) {
//            Text(text = "Thanh toán", color = Color.White, style = TextStyle(fontSize = 16.sp))
//        }
//    }
//
//}
//
//@Composable
//fun CartItemCard(item: CartItem, onDelete: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White, shape = RoundedCornerShape(8.dp))
//            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
//            .padding(8.dp)
//    ) {
//        Image(
//            painter = painterResource(id = item.imageRes),
//            contentDescription = item.title,
//            modifier = Modifier
//                .size(80.dp)
//                .clip(RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(
//                text = item.title,
//                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            Text(
//                text = item.description,
//                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Giá: ${item.price}",
//                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
//            )
//
//            Row(
//                modifier = Modifier.padding(top = 8.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = {
//                        if (item.quantity > 1) {
//                            item.quantity -= 1
//                        }
//                    },
//                    modifier = Modifier.size(30.dp)
//                ) {
//                    Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//                }
//
//                Text(
//                    text = item.quantity.toString(),
//                    modifier = Modifier.padding(horizontal = 8.dp),
//                    style = TextStyle(fontSize = 16.sp)
//                )
//
//                IconButton(
//                    onClick = {
//                        item.quantity += 1
//                    },
//                    modifier = Modifier.size(30.dp)
//                ) {
//                    Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//                }
//            }
//
//            Button(
//                onClick = onDelete,
//                modifier = Modifier.padding(top = 8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//                Text(text = "Xóa", color = Color.White)
//            }
//        }
//    }
//}
//
//data class CartItem(
//    val imageRes: Int,
//    val title: String,
//    val description: String,
//    val rating: Double,
//    val price: String,
//    var quantity: Int = 1 // Default quantity to 1
//)
package com.example.shop_thoitrang.ui1.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shop_thoitrang.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(navController: NavController) {
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem(R.drawable.quanje, "Áo khoác nữ mùa đông", "Áo hoodie dày", 4.9, "$57"),
                CartItem(R.drawable.ao_khoac, "Áo khoác nam mùa đông", "Áo dạ dài", 4.8, "$62"),
                CartItem(R.drawable.quangin, "Túi xách nữ có bảy túi", "Túi xách thanh lịch", 4.9, "$68"),
                CartItem(R.drawable.dam, "Đầm nữ dự tiệc", "Đầm dài sang trọng", 4.7, "$75"),
                CartItem(R.drawable.ao_so_mi, "Áo sơ mi nam", "Sơ mi công sở", 4.6, "$35"),
                CartItem(R.drawable.ao_thun, "Áo thun nữ", "Chất liệu cotton", 4.8, "$25"),
                CartItem(R.drawable.damdo, "Quần jeans nữ", "Phong cách trẻ trung", 4.9, "$45"),
                CartItem(R.drawable.ao, "Váy midi nữ", "Chất liệu voan", 4.8, "$50")
            )
        )
    }

    // Bố cục chính của trang
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Nội dung giỏ hàng
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            TopAppBar(
                title = { Text("Giỏ hàng", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

//            Text(
//                text = "Giỏ hàng",
//                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(vertical = 16.dp)
//            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cartItems) { item ->
                    CartItemCard(item = item, onDelete = {
                        cartItems = cartItems.filterNot { it == item }
                    })
                }
            }
        }

        // Nút thanh toán ở cuối màn hình
        Button(
            onClick = { navController.navigate("checkout") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter), // Căn ở cuối màn hình
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text(text = "Thanh toán", color = Color.White, style = TextStyle(fontSize = 16.sp))
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description,
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Giá: ${item.price}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (item.quantity > 1) {
                            item.quantity -= 1
                        }
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Text("-", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(fontSize = 16.sp)
                )

                IconButton(
                    onClick = {
                        item.quantity += 1
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = onDelete,
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Xóa", color = Color.White)
            }
        }
    }
}

data class CartItem(
    val imageRes: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val price: String,
    var quantity: Int = 1 // Default quantity to 1
)
