package com.riveong.animalink.ui.screen

sealed class Screen(val route: String) {
    //in navbar
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Forum : Screen("forum")
    object History : Screen("history")
    object Profile : Screen("profile")
    //not in navbar
    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
    object Detail : Screen("detail/{ListingID}") {
        fun createRoute(ListingID: Long) = "detail/$ListingID"
    }

}