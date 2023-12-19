package com.riveong.animalink.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.ui.components.reuseable.LatestAnimal
import com.riveong.animalink.ui.components.reuseable.LatestAnimalsRow
import com.riveong.animalink.ui.components.reuseable.LatestProductRow
import com.riveong.animalink.data.model.animalsDummy
import com.riveong.animalink.data.model.productDummy
import com.riveong.animalink.ui.theme.primary
import com.riveong.animalink.ui.theme.secondary

@Composable
fun headerFull(modifier: Modifier = Modifier,username: String){
Column(
    modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())

) {
    header(username = username)
    banner()
    featureMenu()
    LatestAnimalsRow(listAnimals = animalsDummy)
    LatestProductRow(listProduct = productDummy)

}


}
@Composable
fun header(username: String = "Jamal", modifier: Modifier = Modifier){

    Row (
        modifier
            .fillMaxWidth()
            .padding(27.dp)
    ){

        AsyncImage(
            model = "https://enoughproject.org/wp-content/uploads/2017/04/Ryan_Gosling-e1493121669188-300x300.jpg",
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(20.dp)
        )

        Spacer(
            modifier = Modifier.width(9.dp)
        )

        Text(
            style = TextStyle(fontWeight = FontWeight(700)),
            text = "Welcome "
        )
        Text(
            style = TextStyle(
                color = Color(0xFFF47D25),
                fontWeight = FontWeight(700)
            ),
            text = username
        )

        Spacer(
            Modifier.weight(1f)

        )

        Box(
            Modifier
                .width(75.dp)
                .height(23.dp)
                .background(color = primary, shape = RoundedCornerShape(size = 16.5.dp)),
            contentAlignment = Alignment.Center,

            ){
            Text(
                text = "Member",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
            )
        }

    }

}

@Composable
fun banner(modifier: Modifier = Modifier){
Column(
    Modifier
        .fillMaxWidth()
        .padding(horizontal = 27.dp)) {
        Image(
            painterResource(R.drawable.banner),
            null,
            Modifier
                .align(Alignment.CenterHorizontally)
                .width(356.dp)
                .height(158.dp)
                .background(color = primary, shape = RoundedCornerShape(24.dp))

        )
}
        Spacer(modifier = Modifier.height(28.dp))
    }

@Composable
fun featureMenu(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp)) {


        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)

        ){
            //1
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(62.dp)
                    .height(62.dp)
                    .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = "VetLink",
                    textAlign = TextAlign.Center,
                    color = Color.White,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                    )
                )
            }

            //2
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(62.dp)
                    .height(62.dp)
                    .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = "ZooLink",
                    textAlign = TextAlign.Center,
                    color = Color.White,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                    )


                )
            }

            //3
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(62.dp)
                    .height(62.dp)
                    .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = "LensLink",
                    textAlign = TextAlign.Center,
                    color = Color.White,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                    )
                )
            }

            //4
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(62.dp)
                    .height(62.dp)
                    .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = "InfoLink",
                    textAlign = TextAlign.Center,
                    color = Color.White,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(700),
                    )
                )
            }


        }
    }
}

@Composable
@Preview(showBackground = true)
fun testheader(){

        headerFull(username = "jamal")



}