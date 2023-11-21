package com.example.foodie.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodie.R


@Composable
fun Cart(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = "Shopping Cart", style = MaterialTheme.typography.h6, color= Color.White) },
            navigationIcon = {
                IconButton(onClick = { /* Handle back button action */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = { /* Handle cart icon action */ }) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart",tint = Color.White)
                }
            },
            backgroundColor = Color(android.graphics.Color.parseColor("#CF471E"))
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(getDummyCartItems()) { cartItem ->
                CartItemCard(cartItem = cartItem)
            }

            // Add some spacing between the LazyColumn and the TotalAmountButton
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }

            // Add the TotalAmountButton
            item {
                TotalAmountButton(totalAmount = "$45.00", onClick = { /* Handle payment action */ })
            }
        }
    }
}

fun getDummyCartItems(): List<CartItem> {
    return listOf(
        CartItem(name = "Pepperonica", price = "$10.00"),
        CartItem(name = "Beaf Pizza", price = "$20.00"),
        CartItem(name = "Veg Pizza", price = "$15.00")
    )
}

@Composable
fun TotalAmountButton(totalAmount: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp).padding(start = 30.dp, end = 30.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(android.graphics.Color.parseColor("#CF471E"))),
    ) {
        Text(
            text = "Pay Total Amount: $totalAmount",
            style = MaterialTheme.typography.button,
            color = Color.White
        )
    }
}


@Composable
fun CartItemCard(cartItem: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(android.graphics.Color.parseColor("#FBE1D9")),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image (Replace R.drawable.pizza with the actual resource ID for the image)
            Image(
                painter = painterResource(id = R.drawable.pizza),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Product Details
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = cartItem.name, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Price: ${cartItem.price}", fontSize = 12.sp)
            }

            // Counter Section
            CounterSection()
        }
    }
}


@Composable
fun CounterSection() {
    var count by remember { mutableStateOf(1) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { if (count > 1) count-- },
            modifier = Modifier
                .size(13.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Remove"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = count.toString(), style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = { count++ },
            modifier = Modifier
                .size(13.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}


data class CartItem(val name: String, val price: String)