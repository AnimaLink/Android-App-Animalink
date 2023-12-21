package com.riveong.animalink.ui.components.additional

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.riveong.animalink.data.model.News
import com.riveong.animalink.data.model.NewsDummy
import com.riveong.animalink.data.model.Product
import com.riveong.animalink.ui.components.reuseable.LatestAnimal
import com.riveong.animalink.ui.components.reuseable.News
import com.riveong.animalink.ui.components.reuseable.NewsBetter
import com.riveong.animalink.ui.components.reuseable.checkIcon
import com.riveong.animalink.ui.theme.primary

@Composable
@Preview
fun NewsLink(
    modifier: Modifier = Modifier,
    listNewsz: List<News>  = NewsDummy
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {

        NewsBetter(
            animal = Product(R.drawable.cat, "Kucing ternyata bisa sembuhkan depresi! Siapa nih yang barusan tahu kucing bisa sembuhkan depresi? Yap apakah kalian pernah perhatikan...", "Kocheng!","type moon"),
        )
        Column(Modifier.padding(27.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Today's News",
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 25.sp
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(19.dp),
                modifier = modifier
            ) {
                items(listNewsz, key = { it.title }) { animol ->
                    NewsItems(news = animol)
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}


@Composable
fun NewsItems(news: News, modifier: Modifier = Modifier){
    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .width(200.dp)
            .height(370.dp)

        ,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ){

        Column {

            AsyncImage(
                model = news.image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = news.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = checkIcon(news.short),
                    maxLines = 5,
                    style = MaterialTheme.typography.titleSmall,
                )

            }
        }
    }
}