package com.riveong.animalink.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.riveong.animalink.Ihate
import com.riveong.animalink.ui.components.auth.login
import com.riveong.animalink.ui.components.auth.register
import com.riveong.animalink.ui.components.home.headerFull
import com.riveong.animalink.ui.components.splash.splash
import com.riveong.animalink.ui.screen.Screen

@Composable
fun logic(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier
    ){
        composable(
            route = Screen.Splash.route
        ) {
            splash(navHostController = navController)
        }
        composable(
            route = Screen.Login.route
        ) {
            login(navHostController = navController)
        }
        composable(
            route = Screen.Register.route
        ) {
            register(navHostController = navController)
        }
        composable(
            route = Screen.Home.route
        ) {
            Ihate()
        }
    }
}