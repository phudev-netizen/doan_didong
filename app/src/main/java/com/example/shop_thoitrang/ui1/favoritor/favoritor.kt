package com.example.shop_thoitrang.ui1.favoritor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun FavoritePage(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        // Header
        Text(
            text = "Favorites",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sample data
        val favoriteItems = listOf(
            FavoriteItem(R.drawable.quanje, "Women Winter Clothes", "Thick fleece hoodie top", 4.9, "$57"),
            FavoriteItem(R.drawable.ao_khoac, "Waterproof Leather Watch", "Stylish environmental IPS bronze", 4.1, "$46"),
            FavoriteItem(R.drawable.quangin, "Seven Pocket Women Bag", "Elegant handbag for ladies", 4.9, "$68"),
            FavoriteItem(R.drawable.dam, "Silver Ring Set Women", "Jewelry Type: Rings Certificate", 4.6, "$70"),
            FavoriteItem(R.drawable.ao_so_mi, "LouisWill Men Sunglasses", "Polarized sunglasses for men", 5.0, "$50")
        )

        // LazyColumn for listing favorite items
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f) // Makes LazyColumn take available space
        ) {
            items(favoriteItems.size) { index ->
                FavoriteItemCard(item = favoriteItems[index], onAddToCart = {
                    // Handle "Add to Cart" action here
                })
            }
        }

        // Footer will always be at the bottom
        Footer(navController)
    }
}

// Data class for favorite item
data class FavoriteItem(
    val imageRes: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val price: String
)

@Composable
fun FavoriteItemCard(item: FavoriteItem, onAddToCart: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        // Product Image
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Product Details
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rating Star
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFD700), // Gold color
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${item.rating}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                // Price
                Text(
                    text = item.price,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Add to Cart Button
        Button(
            onClick = onAddToCart,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)), // Orange color
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(text = "Add to Cart", color = Color.White, style = TextStyle(fontSize = 14.sp))
        }
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
