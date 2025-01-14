

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class CartItem(val name: String, var quantity: Int)

class CartViewModel : ViewModel() {
    var cartItems = mutableStateOf<List<CartItem>>(emptyList())
        private set

    fun addToCart(productName: String) {
        val existingItem = cartItems.value.find { it.name == productName }
        if (existingItem != null) {
            // Tăng số lượng nếu sản phẩm đã tồn tại
            val updatedItems = cartItems.value.map {
                if (it.name == productName) it.copy(quantity = it.quantity + 1) else it
            }
            cartItems.value = updatedItems
        } else {
            // Thêm sản phẩm mới
            cartItems.value = cartItems.value + CartItem(name = productName, quantity = 1)
        }
    }
}

