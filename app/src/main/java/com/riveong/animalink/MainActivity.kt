package com.riveong.animalink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.riveong.animalink.ui.components.auth.login
import com.riveong.animalink.ui.components.auth.register
import com.riveong.animalink.ui.components.detail.DetailProduct
import com.riveong.animalink.ui.components.home.header
import com.riveong.animalink.ui.components.home.headerFull
import com.riveong.animalink.ui.components.profile.User
import com.riveong.animalink.ui.components.reuseable.ListAnimalPage
import com.riveong.animalink.ui.components.reuseable.ListProductPage
import com.riveong.animalink.data.model.animalsDummy
import com.riveong.animalink.data.model.productDummy
import com.riveong.animalink.ui.components.chat.Chat
import com.riveong.animalink.ui.components.history.History
import com.riveong.animalink.ui.components.splash.splash
import com.riveong.animalink.ui.logic
import com.riveong.animalink.ui.screen.NavigationItem
import com.riveong.animalink.ui.screen.Screen
import com.riveong.animalink.ui.theme.AnimalinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalinkTheme {
                //register()
                //splash()
                //register = register()
                //login = login()
                //register()
                //home = headerFull(username = "Jamal")
                //product list page =  ListProductPage(produk = productDummy)
                //ListAnimalPage(hewan = animalsDummy)
                //user = User()

                //detail product = DetailProduct("")
                //DetailProduct(title = "Ayam")
                logic()


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
private fun BottomBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
    ) {
        val navigationItems = listOf(
            NavigationItem(
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                icon = Icons.Default.Email,
                screen = Screen.Chat
            ),
            NavigationItem(
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Forum
            ),
            NavigationItem(
                icon = Icons.Default.Refresh,
                screen = Screen.History
            ),
            NavigationItem(
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun Ihate(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                headerFull(username = "jamal")
            }
            composable(Screen.Chat.route) {
                Chat()
            }
            composable(Screen.Profile.route) {
                User()
            }
            composable(Screen.History.route) {
                History()
            }
            composable(Screen.Forum.route) {
                ListAnimalPage(hewan = animalsDummy)
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimalinkTheme {
        headerFull(username = "Jamal")
    }
}