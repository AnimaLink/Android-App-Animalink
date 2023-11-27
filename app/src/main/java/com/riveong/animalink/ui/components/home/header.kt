package com.riveong.animalink.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.riveong.animalink.R

@Composable
fun header(modifier: Modifier = Modifier,username: String = "Jamal"){

    Row (
        modifier
            .fillMaxWidth()
            .padding(27.dp)
    ){

        AsyncImage(
            model = "https://live.staticflickr.com/65535/52611956370_e3f30b1dee_z.jpg",
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
@Preview(showBackground = true)
fun testheader(){
    header()
}