package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavHostController
import com.riveong.animalink.data.model.Animal

@Composable
fun ListAnimalPage(hewan:List<Animal>, modifier: Modifier = Modifier, navHostController: NavHostController,navigateToDetail: (Long) -> Unit,){
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )

    { 
        //searchBar()
        ListAnimals(listAnimal = hewan, navHostController = navHostController, navigateToDetail =  navigateToDetail)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAnimals(
    listAnimal: List<Animal>,
    modifier: Modifier = Modifier,
    navHostController: NavHostController,navigateToDetail: (Long) -> Unit,
) {

    Column{

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
            items(listAnimal, key = { it.id }) { animol ->
                LatestAnimal(animal = animol,navHostController = navHostController, navigateToDetail = navigateToDetail)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

