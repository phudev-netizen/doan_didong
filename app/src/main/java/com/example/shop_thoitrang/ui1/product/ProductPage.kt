
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shop_thoitrang.R
import com.example.shop_thoitrang.RetrofitClient3
import com.example.shop_thoitrang.ui1.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val categories = listOf(
    "Áo Thun",
    "Quần Jeans",
    "Đầm",
    "Áo Vest",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPage(navController: NavController, danhMucId: Int) {
    var products by remember { mutableStateOf<List<Product>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(danhMucId) {
        try {
            isLoading = true
            errorMessage = null
            val response = withContext(Dispatchers.IO) {
                RetrofitClient3.apiService.getProducts(danhMucId)
            }
            if (response.status == "success") {
                products = response.products
            } else {
                errorMessage = response.message
            }
        } catch (e: Exception) {
            errorMessage = "Không thể tải sản phẩm: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sản phẩm") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Đã xảy ra lỗi",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                products.isEmpty() -> {
                    Text(
                        text = "Không có sản phẩm nào",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(products.size) { index ->
                            ProductCard(navController = navController, product = products[index])
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductCard(navController: NavController, product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = {
            // Điều hướng tới trang chi tiết sản phẩm
            navController.navigate("productDetail_ao/${product.Danh_muc_ID}")
        },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "http://192.168.3.49/ShoppFashions/images/${product.Hinh_anh}", // Đường dẫn hình ảnh
                contentDescription = product.Ten_san_pham,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(product.Ten_san_pham, fontWeight = FontWeight.Bold)
            Text("${product.Gia} VND")
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
