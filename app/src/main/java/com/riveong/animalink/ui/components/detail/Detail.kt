package com.riveong.animalink.ui.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.riveong.animalink.R

@Composable
fun DetailProduct(modifier: Modifier = Modifier, title:String = "Bebek", price:String = "Rp 250.000.000", description:String = "Dijual bebek jantan, nego tipis, berat banget dua belasan. Bebek ini bukan bebek biasa, dia bisa bermain piano dan menyanyi lagu pop. Dia juga suka makan pizza dan minum kopi. Dia memiliki hobi unik, yaitu berenang di kolam renang sambil membaca buku. Dia juga pandai bermain catur dan selalu menang melawan angsa tetangga."){
    Column(Modifier
        .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://enoughproject.org/wp-content/uploads/2017/04/Ryan_Gosling-e1493121669188-300x300.jpg",
            modifier = Modifier.fillMaxWidth()
                .height(360.dp),
            contentDescription = "item picture",
            contentScale = ContentScale.FillBounds
            )
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 700.dp)
                .offset(y = -24.dp)

                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(
                        topStart = 27.dp,
                        topEnd = 27.dp
                    )
                )
        ){
            Column(modifier = Modifier.padding(top = 33.dp,start = 33.dp,end = 33.dp)) {
                Text(text = title,
                    style = TextStyle(
                        fontSize = 32.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    )
                )
                Text(text = price,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color(0xFF000000),
                        )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = description)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Comments",
                style = TextStyle(
                        fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                    )
                )
            }



        }
    }

}

@Composable
@Preview
fun testDetail(){
    DetailProduct()
}