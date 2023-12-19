package com.riveong.animalink.ui.screen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Forum : Screen("forum")
    object History : Screen("history")
    object Profile : Screen("profile")
}