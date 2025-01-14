package com.example.shop_thoitrang
import com.google.android.gms.analytics.ecommerce.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


object RetrofitClient1 {
    private const val BASE_URL = "http://192.168.3.49/ShoppFashions/api/nguoi_dung/"

    val instance: ApiService1 by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService1::class.java)
    }
}

// cái này là đăng nhập
interface ApiService1 {
    @POST("login.php")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}

data class LoginResponse(
    val status: String,
    val message: String,
    val user: User
)

data class User(
    val username: String,
    val password: String
)
data class LoginRequest(
    val username: String,
    val password: String
)
//cái này là đăng kí
object RetrofitClient2 {
    private const val BASE_URL = "http://192.168.3.49/ShoppFashions/api/nguoi_dung/"

    val instance: ApiService2 by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService2::class.java)
    }
}
interface ApiService2 {
    @POST("register.php")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>
}
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

// Lớp phản hồi đăng ký
data class RegisterResponse(
    val status: String,
    val message: String
)

// ket noi trang san pham
object RetrofitClient3 {
    private const val BASE_URL = "http://192.168.3.49/ShoppFashions/api/san_pham/" // Đổi thành URL của server

    val apiService: ApiService3 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService3::class.java)
    }
}
data class ApiResponse(
    val status: String,
    val message: String,
    val products: List<com.example.shop_thoitrang.ui1.product.Product>
)


interface ApiService3 {
    @GET("load.php")
    suspend fun getProducts(@Query("Danh_muc_ID") danhMucId: Int): ApiResponse
}
