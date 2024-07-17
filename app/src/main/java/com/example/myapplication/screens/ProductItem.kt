package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.Product

@Composable
fun ProductItem(
    product: Product = Product(
        id = "1",
        name = "Shoe black",
        color = Color.Black,
        price = 1200f,
        discountPrice = 1100f,
        size = 9,
        rating = 4.3f,
        imageRes = R.drawable.blue
    ),
    onClickProductItem: () -> Unit,
    onFavouriteClicked: (isFavourite : Boolean, id: String) -> Unit
) {
    var backColor = remember {
        product.color
    }
    var isFavourite by remember {
        mutableStateOf(product.isSelectedFavourite)
    }

    Box(

        modifier = Modifier
            .padding(20.dp)
            .size(168.dp, 220.dp)
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(.2f)
                .background(color = backColor, shape = RoundedCornerShape(8.dp))
                .clickable { onClickProductItem.invoke() }
        )
        IconButton(onClick = {
            isFavourite = !isFavourite
            onFavouriteClicked.invoke(isFavourite, product.id)
        }, modifier = Modifier.align(Alignment.TopStart)) {
            println("isSelectedFavourite ${product.isSelectedFavourite}")
            Icon(
                imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = product.size.toString(),
            fontSize = 128.sp,
            fontWeight = FontWeight.Bold,
            color = backColor.copy(alpha = .3f)
        )
        Image(
            painter = painterResource(id = product.imageRes), contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .rotate(-30f)
                .offset(x = 30.dp, y = 20.dp)
        )
        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            Text(
                text = "Rs ${product.discountPrice}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Rs ${product.price}",
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp).align(Alignment.End),
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
        }


    }


}

