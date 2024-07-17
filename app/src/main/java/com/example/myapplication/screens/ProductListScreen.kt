package com.example.myapplication.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.Product
import com.example.myapplication.navigation.NavigationItem

var defaultList = getProductList()
@Composable
fun ProductListScreen(navController: NavController) {
    var productList = remember {
        defaultList
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(16.dp)) {
        items(productList) { it ->
            ProductItem(product = it, onClickProductItem = {
                println("Clicked on Item ${it.id}")
                navController.navigate("${NavigationItem.PRODUCT_DETAILS}/${it.id}")
            }, onFavouriteClicked = { isFavourite: Boolean, id: String ->
                updateProductListWithId(id, isFavourite)
            })
        }
    }
}

fun updateProductListWithId(pickedId : String, isFavourite: Boolean) {
    val newList = defaultList
    newList.find { selectedItem -> selectedItem.id == pickedId }?.isSelectedFavourite = isFavourite
    defaultList = newList
}
fun getProductList(): List<Product> {
    println("Called 1--->")
    return mutableListOf(
        Product(
            id = "1",
            name = "Shoe black",
            color = Color.Black,
            price = 1200f,
            discountPrice = 1100f,
            size = 9,
            rating = 4.3f,
            imageRes = R.drawable.black
        ), Product(
            id = "2",
            name = "Shoe Blue",
            color = Color.Blue,
            price = 1400f,
            discountPrice = 1160f,
            size = 8,
            rating = 4.2f,
            imageRes = R.drawable.blue
        ), Product(
            id = "3",
            name = "Shoe Dark Green",
            color = Color.Green,
            price = 1100f,
            discountPrice = 1600f,
            size = 7,
            rating = 4.1f,
            imageRes = R.drawable.dark_green
        ), Product(
            id = "4",
            name = "Shoe blue orange",
            color = Color.Magenta,
            price = 700f,
            discountPrice = 1100f,
            size = 9,
            rating = 3.6f,
            imageRes = R.drawable.blue_orange
        ), Product(
            id = "5",
            name = "Shoe Brown",
            color = Color.Cyan,
            price = 1240f,
            discountPrice = 1600f,
            size = 9,
            rating = 4.3f,
            imageRes = R.drawable.brown
        ), Product(
            id = "6",
            name = "Shoe Yellow",
            color = Color.Yellow,
            price = 1280f,
            discountPrice = 1800f,
            size = 6,
            rating = 4.3f,
            imageRes = R.drawable.yellow_white
        ), Product(
            id = "7",
            name = "Shoe Pink",
            color = Color.LightGray,
            price = 1260f,
            discountPrice = 1530f,
            size = 9,
            rating = 4.3f,
            imageRes = R.drawable.pink
        ), Product(
            id = "8",
            name = "Shoe Green",
            color = Color.Green,
            price = 1200f,
            discountPrice = 2100f,
            size = 9,
            rating = 4.3f,
            imageRes = R.drawable.green
        ), Product(
            id = "9",
            name = "Shoe Red",
            color = Color.Red,
            price = 3200f,
            discountPrice = 3400f,
            size = 9,
            rating = 4.1f,
            imageRes = R.drawable.red
        ), Product(
            id = "10",
            name = "Shoe Grey",
            color = Color.Gray,
            price = 1820f,
            discountPrice = 2000f,
            size = 9,
            rating = 4.1f,
            imageRes = R.drawable.grey
        )
    )
}