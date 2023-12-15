package com.riveong.animalink.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.ui.theme.primary

@Composable
fun headerFull(modifier: Modifier = Modifier,username: String){
Column(
    Modifier
    .fillMaxWidth()

) {
    header(username = username)
    banner()
    featureMenu()
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
            text = "Welcome $username!"
        )

        Spacer(
            Modifier.weight(1f)

        )

        Image(
            imageVector = ImageVector.vectorResource(R.drawable.search),
            contentDescription = "search button",
        )

    }

}

@Composable
fun banner(modifier: Modifier = Modifier){
Column(Modifier.fillMaxWidth()) {
        Box(
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
fun featureMenu(textInput: String = "really long test" , modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxWidth()) {


        Row (Modifier.align(Alignment.CenterHorizontally)){
            //1
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(80.dp)
                    .height(80.dp)
                    .background(color = primary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = textInput,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(13.dp))

            //2
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(80.dp)
                    .height(80.dp)
                    .background(color = primary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = textInput,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(13.dp))

            //3
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(80.dp)
                    .height(80.dp)
                    .background(color = primary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = textInput,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(13.dp))

            //4
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier

                    .width(80.dp)
                    .height(80.dp)
                    .background(color = primary, shape = RoundedCornerShape(size = 15.dp))
            ) {
                Text(
                    text = textInput,
                    textAlign = TextAlign.Center,
                    color = Color.White
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