package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riveong.animalink.data.model.Animal

@Composable
fun ListAnimalPage(hewan:List<Animal>, modifier: Modifier = Modifier){
    Column(
        modifier
            .fillMaxWidth()
    )

    { 
        //searchBar()
        ListAnimals(listAnimal = hewan)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAnimals(
    listAnimal: List<Animal>,
    modifier: Modifier = Modifier
) {

    Column(Modifier.padding(start = 27.dp, end = 27.dp, top = 27.dp)) {

        Text(
            text = "Animals",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        SearchBar(
            query = "",
            onQueryChange = {  },
            onSearch = {},
            active = false,
            onActiveChange = {},
            Modifier.height(45.dp)
        ) {

        }
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
                //LatestAnimal(animol)
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}

