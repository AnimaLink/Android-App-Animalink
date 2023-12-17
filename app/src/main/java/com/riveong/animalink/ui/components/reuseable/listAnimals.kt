package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.Product
import com.riveong.animalink.data.model.animalsDummy
import com.riveong.animalink.data.model.productDummy
@Composable
fun ListAnimalPage(hewan:List<Animal>, modifier: Modifier = Modifier){
    Column(
        modifier
            .fillMaxWidth()
    )

    { 
        searchBar()
        ListAnimals(listAnimal = hewan)
    }
}

@Composable
fun ListAnimals(
    listAnimal: List<Animal>,
    modifier: Modifier = Modifier
) {
    Column(Modifier.padding(start = 27.dp, end = 27.dp)) {

        Text(
            text = "Animals",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        //TODO: add categories

        //items
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(19.dp),
            verticalArrangement = Arrangement.spacedBy(19.dp),
            modifier = modifier
        ) {
            items(listAnimal, key = { it.title }) { animol ->
                LatestAnimal(animol)
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}

