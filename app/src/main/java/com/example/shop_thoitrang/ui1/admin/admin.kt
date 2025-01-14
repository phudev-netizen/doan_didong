package com.example.shop_thoitrang.ui1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminOrdersScreen(navController: NavController) {
    var filter by remember { mutableStateOf("Tất cả") }
    var selectedOrder by remember { mutableStateOf<Order?>(null) }
    val orders = remember { generateDummyOrders() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quản lý đơn hàng", color = Color.White) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFF6200EE))
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Bộ lọc
            FilterOptions(filter, onFilterChange = { filter = it })

            // Danh sách đơn hàng
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                val filteredOrders = if (filter == "Tất cả") orders else orders.filter { it.status == filter }
                items(filteredOrders.size) { index ->
                    OrderItem(order = filteredOrders[index], onClick = { selectedOrder = it })
                }
            }
        }

        // Hiển thị chi tiết đơn
        selectedOrder?.let { order ->
            OrderDetailsDialog(
                order = order,
                onClose = { selectedOrder = null },
                onUpdate = { updatedOrder ->
                    selectedOrder = null
                    // Update the order status in your list/database
                }
            )
        }
    }
}

@Composable
fun FilterOptions(selectedFilter: String, onFilterChange: (String) -> Unit) {
    val filters = listOf("Tất cả", "Đang chờ", "Đã xử lý", "Đã giao")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFECEFF1))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        filters.forEach { filter ->
            Text(
                text = filter,
                modifier = Modifier
                    .clickable { onFilterChange(filter) }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                color = if (selectedFilter == filter) Color(0xFF6200EE) else Color.Black,
                style = TextStyle(fontSize = 14.sp, fontWeight = if (selectedFilter == filter) FontWeight.Bold else FontWeight.Normal)
            )
        }
    }
}

@Composable
fun OrderItem(order: Order, onClick: (Order) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(order) }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Mã đơn: ${order.id}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Trạng thái: ${order.status}", color = Color.Gray, fontSize = 14.sp)
                Text("Ngày đặt: ${order.date}", color = Color.Gray, fontSize = 14.sp)
            }
            Text("${order.total} đ", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF6200EE))
        }
    }
}

@Composable
fun OrderDetailsDialog(order: Order, onClose: () -> Unit, onUpdate: (Order) -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = { onUpdate(order.copy(status = "Đã xử lý")) }) {
                Text("Cập nhật trạng thái")
            }
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text("Đóng")
            }
        },
        title = { Text("Chi tiết đơn hàng") },
        text = {
            Column {
                Text("Mã đơn: ${order.id}")
                Text("Ngày đặt: ${order.date}")
                Text("Trạng thái: ${order.status}")
                Text("Tổng tiền: ${order.total} đ")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Danh sách sản phẩm:")
                order.products.forEach {
                    Text("- ${it.name} x ${it.quantity}", fontSize = 14.sp)
                }
            }
        }
    )
}

// Dummy Data
data class Order(val id: String, val status: String, val date: String, val total: String, val products: List<Product>)
data class Product(val name: String, val quantity: Int)

fun generateDummyOrders(): List<Order> {
    return listOf(
        Order("001", "Đang chờ", "2025-01-01", "300,000", listOf(Product("Áo thun", 2), Product("Quần jeans", 1))),
        Order("002", "Đã xử lý", "2025-01-02", "500,000", listOf(Product("Giày thể thao", 1))),
        Order("003", "Đã giao", "2025-01-03", "200,000", listOf(Product("Mũ lưỡi trai", 3))),
    )
}
