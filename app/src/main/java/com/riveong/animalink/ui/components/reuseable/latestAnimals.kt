package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.ui.screen.Screen
import com.riveong.animalink.ui.theme.primary

@Composable
fun LatestAnimal(animal: Animal, modifier: Modifier = Modifier, navHostController: NavHostController, navigateToDetail: (Long) -> Unit,){
    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .width(140.dp)
            .clickable {
                navigateToDetail(animal.id.toLong())
                       }
        ,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ){
        Column {
            AsyncImage(
                model = animal.image,
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
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = checkIcon(animal.species),
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .width(75.dp)
                        .height(23.dp)
                        .background(color = primary, shape = RoundedCornerShape(size = 16.5.dp)),
                    contentAlignment = Alignment.Center,

                    ){
                    Text(
                        text = animal.Status,
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
    }
}

fun checkIcon(species: String): String {
    if (species == "Bird"){
        return "🐦 Bird"
    }
    else if (species == "Snake"){
        return "🐍 Snake"
    }
    else if (species == "Dog"){
        return "🐕 Dog"
    }
    else if (species == "Cat"){
        return "🐈 Cat"
    }
    else if (species == "Cow"){
        return "🐄 Cow"
    }
    else{
        return species
    }

}


@Composable
fun LatestAnimalsRow(
    listAnimals: List<Animal>,
    headline: String = "Latest Listing",
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigateToDetail: (Long) -> Unit,
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
                LatestAnimal(animol, navHostController = navHostController, navigateToDetail = navigateToDetail)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    MaterialTheme {
        //LatestAnimalsRow(animalsDummy)
    }
}