package com.example.shop_thoitrang.ui1
import CartViewModel
import ProductPage
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop_thoitrang.ui1.CheckOut.CheckoutPage
import com.example.shop_thoitrang.ui1.cart.CartPage
import com.example.shop_thoitrang.ui1.favoritor.FavoritePage
import com.example.shop_thoitrang.ui1.home.HomePage
import com.example.shop_thoitrang.ui1.login.ForgotPasswordScreen
import com.example.shop_thoitrang.ui1.login.LoginScreen
import com.example.shop_thoitrang.ui1.login.RegisterScreen
import com.example.shop_thoitrang.ui1.product.ProductDetail.ProductDetail_ao
import com.example.shop_thoitrang.ui1.product.ProductDetail.ProductDetail_dam
import com.example.shop_thoitrang.ui1.product.ProductDetail.ProductDetail_quan
import com.example.shop_thoitrang.ui1.product.ProductDetail.ProductDetail_vest
import com.example.shop_thoitrang.ui1.product.Product_chi_tiet.ProductDetailPage
import com.example.shop_thoitrang.ui1.profile.EditProfilePage
import com.example.shop_thoitrang.ui1.profile.ProfilePage
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        // Màn hình đăng nhập
        composable("login") { LoginScreen(navController) }
        // Màn hình đăng kí
        composable("register") { RegisterScreen(navController) }
        // MÀn hình quên mật khẩu
        composable("ForgotPasswordScreen") { ForgotPasswordScreen(navController) }

        // Trang chủ
        composable("home") { HomePage(navController) }

        // Trang yêu thích
        composable("favorite") { FavoritePage(navController) }

        // Giỏ hàng
        composable("cart") { CartPage(navController) }

        // Thanh toán
        composable("checkout") { CheckoutPage() }
        // Trang hồ sơ
        composable("profile") { ProfilePage(navController) }
        composable("edit_profile") { EditProfilePage(navController) }

        // Trang sản phẩm
        composable("products"){
            ProductPage(navController, danhMucId = 1)
        }
        composable("productDetail_ao") {
            ProductDetail_ao(navController)
        }
        composable("productDetail_quan") {
            ProductDetail_quan(navController)
        }
        composable("productDetail_dam") {
            ProductDetail_dam(navController)
        }
        composable("productDetail_vest") {
            ProductDetail_vest(navController)
        }
        composable("productDetailPage") {
            ProductDetailPage(navController)
        }

//        // Trang sản phẩm
//        composable("products") {
//            ProductPage(
//                onProductClickToAo = { index ->
//                    navController.navigate("product_detail/$index")
//                },
//                onProductClickToQuan = { index ->
//                    navController.navigate("product_detail_quan/$index")
//                }
//            )
//        }
//      composable("product_detail/{productIndex}") { backStackEntry ->
//            val productIndex = backStackEntry.arguments?.getString("productIndex")?.toInt() ?: 0
//            ProductDetail_ao(productIndex = productIndex, navController = navController)
//        }
//
//        composable("product_detail_quan/{productIndex}") { backStackEntry ->
//            val productIndex = backStackEntry.arguments?.getString("productIndex")?.toInt() ?: 0
//            ProductDetail_quan(productIndex = productIndex, navController = navController)
//        }
    }
}
