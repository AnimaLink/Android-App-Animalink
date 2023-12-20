package com.riveong.animalink.ui.components.splash

import android.content.Context
import androidx.compose.foundation.Image
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import com.riveong.animalink.R
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.ui.screen.Screen
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Composable
fun splash(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = store) {
        val saved = wasLoginned(store)
        if (saved){
            navHostController.navigate(Screen.Login.route)
        } else {
            navHostController.navigate(Screen.Home.route)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.logo_dummy),
            contentDescription = null,
            Modifier.width(169.dp)
        )
    }
}


suspend fun wasLoginned(store: ProfileStore): Boolean = suspendCancellableCoroutine { continuation ->
    var saved = true
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    val observer = Observer<String> { data ->
        if (data.isEmpty() && data == "") {
            saved = true
        } else {
            saved = false
        }
        continuation.resume(saved)
    }
    token.observeForever(observer)
    continuation.invokeOnCancellation {
        token.removeObserver(observer)
    }
}

/*@Composable
fun splash(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.logo_dummy),
            contentDescription = null,
            Modifier.width(169.dp)
        )

    }
    var saved = Handler(Looper.getMainLooper()).postDelayed({
        wasLoginned(store)
    }, 5000)

    if (saved){

        navHostController.navigate(Screen.Login.route)
    }

    if(!saved){
        navHostController.navigate(Screen.Home.route)
    }
}

//TODO: if login / if not login

fun wasLoginned(store: ProfileStore): Boolean {
    var saved = true
    GlobalScope.launch(Dispatchers.Main) {
        val token: LiveData<String> = store.getTokenProfile.asLiveData()
        token.observeForever { data: String ->
            print(data)
            if (data.isEmpty() && data == "") {
                saved = true

            } else {
                saved = false
            }
        }



    }
    return saved
}

*/