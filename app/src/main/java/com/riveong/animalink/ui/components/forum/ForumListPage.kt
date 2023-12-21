package com.riveong.animalink.ui.components.forum

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.ui.components.home.getAnimalData
import com.riveong.animalink.ui.components.reuseable.ListAnimalPage
import com.riveong.animalink.ui.screen.Screen

@Composable
fun ForumListPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController, navigateToDetail: (Long) -> Unit,
) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(listOf(Animal(0,"https://static.wikia.nocookie.net/typemoon/images/7/71/Neco-Arc_Remake.png/revision/latest?cb=20210902002059","Neco arc","🗿 Car","Sold"))) }
    val scope = rememberCoroutineScope()


    Column(Modifier.padding(27.dp)) {

        Text(
            text = "Product Listing",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        LaunchedEffect(key1 = store) {
            getAnimalData(store) { anima ->
                data.value = anima ?: listOf()

            }
        }
        ListAnimalPage(hewan = data.value, navHostController = navHostController, navigateToDetail = navigateToDetail)
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .offset(-10.dp,-10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { navHostController.navigate(Screen.NewForum.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            } },
            containerColor = Color(0xFFF47D25),
            contentColor =  Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }




}
