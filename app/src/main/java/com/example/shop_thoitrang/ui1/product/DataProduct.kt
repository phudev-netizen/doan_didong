package com.example.shop_thoitrang.ui1.product


import retrofit2.http.Body
import retrofit2.http.POST
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_thoitrang.RetrofitClient3
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


data class Product(
    val San_pham_ID: Int,
    val Ten_san_pham: String,
    val Mo_ta: String,
    val Gia: String,
    val Soluong_SP: Int,
    val Danh_muc_ID: Int,
    val Mau_sac: String,
    val Kich_thuoc: String,
    val Hinh_anh: String
)


class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun loadProducts(danhMucId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitClient3.apiService.getProducts(danhMucId)
                if (response.status == "success") {
                    _products.value = response.products
                } else {
                    _errorMessage.value = response.message
                }
            } catch (e: Exception) {
                _errorMessage.value = "Không thể tải sản phẩm: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}


