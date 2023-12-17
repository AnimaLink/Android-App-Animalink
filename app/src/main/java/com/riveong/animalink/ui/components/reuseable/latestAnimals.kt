package com.riveong.animalink.ui.components.reuseable

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.riveong.animalink.ui.model.Animal

@Composable
fun LatestAnimal(animal: Animal, modifier: Modifier = Modifier){
    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier.width(140.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ){
        Column {
            Image(
                painter = painterResource(animal.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = animal.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = animal.species,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = "owner: ${animal.Owner}",
                    style = TextStyle(fontSize = 10.sp),
                            fontWeight = FontWeight(600)


                )
            }
        }
    }
}


@Composable
fun LatestAnimalsRow(
    listAnimals: List<Animal>,
    headline: String = "Latest Animals",
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
            items(listAnimals, key = { it.title }) { animol ->
                LatestAnimal(animol)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    MaterialTheme {
        LatestAnimal(
            animal = Animal(R.drawable.necoarc, "Neco arc", "🐈kucing","type moon"),
            modifier = Modifier.padding(8.dp)
        )
    }
}