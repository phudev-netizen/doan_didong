package com.example.shop_thoitrang.ui1.CheckOut

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckoutPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Tiêu đề
        Text(
            text = "Thanh toán",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Địa chỉ giao hàng
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text(
                text = "Nguyễn Thanh Phú\n35/39 Bế Văn Cấm, Phường Tân Kiểng, Quận 7, TP. Hồ Chí Minh",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // Danh sách sản phẩm
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            ProductItem(
                productName = "Quần tây nam Hàn Quốc JBagy dáng baggy",
                price = 189000,
                quantity = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProductItem(
                productName = "Tất Trắng cao cổ Cotton mềm mại",
                price = 0,
                quantity = 1,
                isFreeShipping = true
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // Voucher và phương thức thanh toán
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Shopee Voucher",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Miễn Phí Vận Chuyển",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Phương thức thanh toán",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        PaymentMethodItem(
            methodName = "Thẻ nội địa Napas",
            bankName = "Vietcombank - Ngân hàng Ngoại thương",
            isSelected = true
        )
        PaymentMethodItem(
            methodName = "SPayLater",
            bankName = "Mua trước trả sau đến 12 kỳ",
            isSelected = false
        )
        PaymentMethodItem(
            methodName = "Ví ShopeePay",
            bankName = "",
            isSelected = false
        )

        Divider(color = Color.Gray, thickness = 1.dp)

        // Tổng số tiền
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Chi tiết thanh toán",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng tiền hàng", fontSize = 14.sp, color = Color.Black)
                Text(text = "₫378,000", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng tiền phí vận chuyển", fontSize = 14.sp, color = Color.Black)
                Text(text = "₫65,100", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Giảm giá phí vận chuyển", fontSize = 14.sp, color = Color.Black)
                Text(text = "-₫19,600", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng thanh toán", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
                Text(text = "₫423,500", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Red)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút xác nhận thanh toán
        Button(
            onClick = { /* Xử lý hành động đặt hàng */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
        ) {
            Text(text = "Đặt hàng", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProductItem(productName: String, price: Int, quantity: Int, isFreeShipping: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(0.7f),
                    color = Color.Black
                )
                Text(
                    text = if (isFreeShipping) "Miễn phí" else "₫${price * quantity}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isFreeShipping) Color.Green else Color.Black
                )
            }
            Text(text = "Số lượng: $quantity", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun PaymentMethodItem(methodName: String, bankName: String, isSelected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { /* Xử lý chọn phương thức thanh toán */ },
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0D47A1))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = methodName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            if (bankName.isNotEmpty()) {
                Text(text = bankName, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}
