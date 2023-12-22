package com.riveong.animalink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.riveong.animalink.ui.components.additional.DetectLink
import com.riveong.animalink.ui.components.additional.InfoLink
import com.riveong.animalink.ui.components.additional.NewsLink
import com.riveong.animalink.ui.components.additional.VetLink
import com.riveong.animalink.ui.components.home.headerFull
import com.riveong.animalink.ui.components.profile.User
import com.riveong.animalink.ui.components.chat.Chat
import com.riveong.animalink.ui.components.forum.AddItem
import com.riveong.animalink.ui.components.forum.DetailProduct
import com.riveong.animalink.ui.components.forum.ForumListPage
import com.riveong.animalink.ui.components.forum.NewForum
import com.riveong.animalink.ui.components.history.History
import com.riveong.animalink.ui.logic
import com.riveong.animalink.ui.screen.NavigationItem
import com.riveong.animalink.ui.screen.Screen
import com.riveong.animalink.ui.theme.AnimalinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalinkTheme {
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }

        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                headerFull(username = "jamal", navHostController = navController,navigateToDetail = { ListingID ->
                    navController.navigate(Screen.Detail.createRoute(ListingID))
                })
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
                ForumListPage(navHostController = navController,navigateToDetail = { ListingID ->
                    navController.navigate(Screen.Detail.createRoute(ListingID))
                })
            }
            composable(Screen.NewForum.route) {
                AddItem()
            }
            composable(Screen.DetectLink.route) {
                DetectLink()
            }
            composable(Screen.InfoLink.route) {
                InfoLink()
            }
            composable(Screen.NewsLink.route) {
                NewsLink()
            }
            composable(Screen.VetLink.route) {
                VetLink()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("ListingID") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("ListingID") ?: -1L
                DetailProduct(
                    id = id
                )
            }

            }

            /*composable(Screen.Forum.route) {
                ListAnimalPage(hewan = animalsDummy)
            }*/

        }
    }
