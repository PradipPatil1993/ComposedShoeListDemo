package com.example.myapplication.screens

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay


@Composable
fun ProductDetailsScreen(navController: NavHostController, productID: String = "1") {

    var selectedProduct = defaultList.find {
        it.id == productID
    }
    var selectedColor by remember {
        mutableStateOf(selectedProduct?.color)
    }
    var xOffSet by remember {
        mutableStateOf(800.dp)
    }
    var yOffSet by remember {
        mutableStateOf(800.dp)
    }
    var animateXOffSet = animateDpAsState(
        targetValue = xOffSet,
        label = "",
        animationSpec = tween(durationMillis = 600, easing = FastOutLinearInEasing)
    )
    var animateYOffSet = animateDpAsState(
        targetValue = yOffSet,
        label = "",
        animationSpec = tween(durationMillis = 600, easing = FastOutLinearInEasing)
    )
    var productScale by remember {
        mutableFloatStateOf(.6f)
    }
    var animationProductScale = animateFloatAsState(targetValue = productScale, label = "")
    var productRotate by remember {
        mutableFloatStateOf(-60f)
    }
    var animationProductRotate = animateFloatAsState(targetValue = productRotate, label = "")

    var selectedSize by remember {
        mutableStateOf(selectedProduct?.size.toString())
    }
    var isFavourite by remember {
        mutableStateOf(selectedProduct!!.isSelectedFavourite)
    }

    LaunchedEffect(key1 = true) {
        delay(150)
        xOffSet = 140.dp
        yOffSet = (-130).dp
        productScale = 1f
        productRotate = -30f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Box(
            modifier = Modifier
                .offset(x = animateXOffSet.value, y = animateYOffSet.value)
                .alpha(.3f)
                .size(400.dp)
                .background(shape = CircleShape, color = selectedColor!!)
        )

        IconButton(
            onClick = { navController.popBackStack() }, modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .shadow(
                    elevation = 24.dp,
                    spotColor = DefaultShadowColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .size(36.dp)
        ) {

            Icon(
                imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null
            )

        }

        Column(

            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = selectedProduct?.imageRes!!),
                contentDescription = null,
                modifier = Modifier
                    .scale(animationProductScale.value)
                    .rotate(animationProductRotate.value)
                    .padding(top = 30.dp, end = 48.dp)
                    .size(320.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Sneaker",
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                    Text(
                        text = selectedProduct.name,
                        color = Color.Black,
                        fontSize = 22.sp,
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                    )
                    Row(
                        modifier = Modifier.padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = null,
                            tint = Color(0XFFFFDA45)
                        )
                        Text(
                            text = selectedProduct.rating.toString(),
                            color = Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 4.dp),
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                        )
                    }
                }
                Text(
                    text = "Rs ${selectedProduct.discountPrice}",
                    color = Color.Black,
                    fontSize = 36.sp,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )

            }

            Text(
                text = "Size",
                color = Color.Black,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                ProductSizeCard(size = "8", isSelected = selectedSize == "8") {
                    selectedSize = "8"
                }
                ProductSizeCard(size = "9", isSelected = selectedSize == "9") {
                    selectedSize = "9"
                }
                ProductSizeCard(size = "10", isSelected = selectedSize == "10") {
                    selectedSize = "10"
                }
                ProductSizeCard(size = "11", isSelected = selectedSize == "11") {
                    selectedSize = "11"
                }
            }

            Text(
                text = "Color",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                ProductColorCard(color = Color.Red, isSelected = selectedColor == Color.Red) {
                    selectedColor = Color.Red
                }
                ProductColorCard(color = Color.Blue, isSelected = selectedColor == Color.Blue) {
                    selectedColor = Color.Blue
                }
                ProductColorCard(
                    color = Color.Magenta,
                    isSelected = selectedColor == Color.Magenta
                ) {
                    selectedColor = Color.Magenta
                }
                ProductColorCard(color = Color.Yellow, isSelected = selectedColor == Color.Yellow) {
                    selectedColor = Color.Yellow
                }
                ProductColorCard(color = Color.Black, isSelected = selectedColor == Color.Black) {
                    selectedColor = Color.Black
                }
            }

            Text(
                text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words",
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 10.dp),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .padding(horizontal = 22.dp, vertical = 20.dp)
                    .align(Alignment.End)

            ) {

                IconButton(onClick = {
                    isFavourite = !isFavourite
                    updateProductListWithId(productID, isFavourite)
                }) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavourite) Color.Red else MaterialTheme.colorScheme.surfaceTint
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 8.dp), shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null)
                    Text(text = "Add to Cart")
                }

            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductColorCardPreview() {
    ProductColorCard {

    }
}

@Composable
fun ProductSizeCard(
    modifier: Modifier = Modifier,
    size: String = "8",
    isSelected: Boolean = true,
    onClick: () -> Unit

) {
    var backGroundColor = if (isSelected) Color.Blue else Color.White
    var border = if (isSelected) 0.dp else 0.8.dp
    var textColor = if (isSelected) Color.White else Color.Black

    Text(text = size,
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .border(width = border, color = Color.Black, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .background(backGroundColor)
            .padding(12.dp),
        color = textColor
    )
}

@Composable
fun ProductColorCard(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    isSelected: Boolean = true,
    onClick: () -> Unit

) {
    var borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent

    Box(
        modifier = modifier
            .border(width = 1.dp, shape = CircleShape, color = borderColor)
            .padding(4.dp)
            .background(color, shape = CircleShape)
            .size(24.dp)
            .clip(CircleShape)
            .clickable { onClick.invoke() }
    ) {

    }
}



