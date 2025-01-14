package com.example.shop_thoitrang.ui1.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop_thoitrang.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun HomePage(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Fixed Header
        Header(navController)

        // Scrollable Content
        Box(modifier = Modifier.weight(1f)) { // This allows the content to take up remaining space
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                content = {
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                    item { Content(navController) }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            )
        }

        // Fixed Footer
        Footer(navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navController: NavController, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text("Fashion shop",
                style = TextStyle(fontSize = 28.sp,
                    fontWeight = FontWeight.Bold)
            )
        },
        actions = {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    //imageVector = Icons.Default.ShoppingCart,
                    painter = painterResource(id = R.drawable.ic_cart_filled),
                    contentDescription = "Cart",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.navigate("cart")
                        }
                )
            }
        },

        colors = TopAppBarDefaults.smallTopAppBarColors
            (containerColor = Color(0xFFF4C2C2),Color(0xFFF4C2C2))
    )
}

@Composable
fun Content(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Promotions Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            Text("Giảm Giá", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        }

        // Promotions data
        val promotions = listOf(
            Pair(R.drawable.khuyen_mai1, "Ưu đãi chp người mới ngayyyy!! Giảm giá 20% cho đơn hàng đầu tiên"),
            Pair(R.drawable.khuyen_mai3, "Mùa hè oi ả ?  Giảm giá 30% cho sản phẩm mùa hè"),
            Pair(R.drawable.khuyen_mai2, "Mua 1 tặng 1 tất cả sản phẩm"),
            Pair(R.drawable.km1, "Giảm giá 20% cho đơn hàng đầu tiên"),
            Pair(R.drawable.km2, "Mua 1 tặng 1 tất cả sản phẩm"),
            Pair(R.drawable.km3, "Giảm giá 30% cho sản phẩm mùa hè")
        )

        // State for LazyRow
        val listState = rememberLazyListState()

        // Auto-scroll logic
        LaunchedEffect(Unit) {
            while (true) {
                for (i in promotions.indices) {
                    listState.animateScrollToItem(i)
                    delay(3000) // Delay between scrolls
                }
            }
        }

        LazyRow(
            state = listState,
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(promotions.size) { index ->
                Card(
                    modifier = Modifier
                        .size(width = 427.dp, height = 200.dp),
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = promotions[index].first),
                            contentDescription = promotions[index].second,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                promotions[index].second,
                                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,

                                )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Categories Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Loại Sản Phẩm", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }

        val categories = listOf(
            Pair(R.drawable.ao_khoac, "Áo Thun"),
            Pair(R.drawable.quanje, "Quần Jeans"),
            Pair(R.drawable.ao_so_mi, "Đầm"),
            Pair(R.drawable.ao_thun, "Áo Khoác"),
            Pair(R.drawable.quanje, "Phụ Kiện")
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories.size) { index ->
                Column(
                    modifier = Modifier.width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(2.dp, Color.Gray, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = categories[index].first),
                            contentDescription = categories[index].second,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        categories[index].second,
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Featured Products Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Sản Phẩm Nổi Bật", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
//            Text("See All", style = TextStyle(fontSize = 14.sp, color = Color.Gray))
        }

        Spacer(modifier = Modifier.height(16.dp))

        val products = listOf(
            Triple(R.drawable.ao_so_mi, "Áo sơ mi MU siêu cấp", 100.00),
            Triple(R.drawable.ao, "Áo thun chất liệu tơ tằm", 80.00),
            Triple(R.drawable.dam, "Đầm trắng từ ngọc trai", 120.00),
            Triple(R.drawable.ao_khoac, "Áo Flanel làm từ da Khủng Long", 200.00),
            Triple(R.drawable.quanje, "Quần Jeans da bò Tây Tạng", 150.00)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.size) { index ->
                Card(
                    modifier = Modifier
                        .size(width = 180.dp, height = 320.dp),
                    onClick = { navController.navigate("ProductDetailPage/$index") }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        // Product Image with Discount Badge
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            Image(
                                painter = painterResource(id = products[index].first),
                                contentDescription = products[index].second,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            // Discount Badge
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .background(Color.Red, shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "-57%",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Highlighted Labels
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFFF5A5F), shape = RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    "15.1",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                "FREESHIP XTRA",
                                color = Color(0xFF008577),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Product Name
                        Text(
                            products[index].second,
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Price with Discount and Original Price
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${products[index].third}₫",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "120.000₫", // Example original price
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Additional Info
                        Text(
                            text = "Đã bán 15,7k",
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Star Rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            repeat(5) { starIndex ->
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star Rating",
                                    tint = if (starIndex < 4) Color(0xFFFFC107) else Color.Gray, // 4 out of 5 stars
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "4.0", // Example rating value
                                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
    Spacer(modifier = Modifier.height(16.dp))

    // Featured Products Section
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Sản Phẩm Mới", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
//            Text("See All", style = TextStyle(fontSize = 14.sp, color = Color.Gray))
    }

    Spacer(modifier = Modifier.height(16.dp))

    val products = listOf(
        Triple(R.drawable.ao_so_mi, "Áo sơ mi MU siêu cấp", 100.00),
        Triple(R.drawable.ao, "Áo thun chất liệu tơ tằm", 80.00),
        Triple(R.drawable.dam, "Đầm trắng từ ngọc trai", 120.00),
        Triple(R.drawable.ao_khoac, "Áo Flanel làm từ da Khủng Long", 200.00),
        Triple(R.drawable.quanje, "Quần Jeans da bò Tây Tạng", 150.00)
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            Card(
                modifier = Modifier
                    .size(width = 180.dp, height = 320.dp),
                onClick = { navController.navigate("ProductDetailPage/$index") }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    // Product Image with Discount Badge
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) {
                        Image(
                            painter = painterResource(id = products[index].first),
                            contentDescription = products[index].second,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Discount Badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .background(Color.Red, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "-57%",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Highlighted Labels
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFF5A5F), shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                "15.1",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "FREESHIP XTRA",
                            color = Color(0xFF008577),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Product Name
                    Text(
                        products[index].second,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Price with Discount and Original Price
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${products[index].third}₫",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "120.000₫", // Example original price
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Additional Info
                    Text(
                        text = "Đã bán 15,7k",
                        style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Star Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        repeat(5) { starIndex ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Rating",
                                tint = if (starIndex < 4) Color(0xFFFFC107) else Color.Gray, // 4 out of 5 stars
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "4.0", // Example rating value
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))



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
