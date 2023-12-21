package com.riveong.animalink.ui.components.reuseable


import android.graphics.Paint.Style
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riveong.animalink.R
import com.riveong.animalink.data.model.Product

@Composable
fun News(animal: Product, modifier: Modifier = Modifier){
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color(0xFF25C5F5))

    ){
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(
                text = "NewsLink!",
                color = Color.White,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                ))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(animal.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier.padding(18.dp),
                ) {
                Text(
                    text = animal.type,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style =
                    TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                )

                Text(
                    text = animal.title,
                    color = Color.White,
                    style = TextStyle(fontSize = 11.sp,)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier
                        .width(100.dp)
                        .height(32.dp),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Text(
                        text = "Read More",
                        fontSize = 10.sp,
                        color = Color.White
                    )

                }
            }
            }
        }
    }
}

@Composable
fun NewsBetter(animal: Product, modifier: Modifier = Modifier){
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color(0xFF25C5F5))

    ){
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(
                text = "NewsLink!",
                color = Color.White,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                ))
            Text(
                text = "Linkin every paws in the park!",
                color = Color.White,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ))

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(animal.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    modifier = Modifier.padding(18.dp),
                ) {
                    Text(
                        text = animal.type,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        style =
                        TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                    )

                    Text(
                        text = animal.title,
                        color = Color.White,
                        style = TextStyle(fontSize = 11.sp,)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier
                            .width(100.dp)
                            .height(32.dp),
                        border = BorderStroke(1.dp, Color.White),
                    ) {
                        Text(
                            text = "Read More",
                            fontSize = 10.sp,
                            color = Color.White
                        )

                    }
                }
            }
        }
    }
}



@Composable
fun NewsItem(
    listProduct: List<Product>,
    headline: String = "Latest Produk",
    modifier: Modifier = Modifier
) {
    Column(Modifier.padding(27.dp)) {


        Text(
            text = headline,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(listProduct, key = { it.title }) { animol ->
                News(animol)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun CategoryItemPreview() {
    MaterialTheme {
        News(
            animal = Product(R.drawable.cat, "Kucing ternyata bisa sembuhkan depresi! Siapa nih yang barusan tahu kucing bisa sembuhkan depresi? Yap apakah kalian pernah perhatikan...", "Kocheng!","type moon"),
        )
    }
}