package com.riveong.animalink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.riveong.animalink.ui.components.auth.login
import com.riveong.animalink.ui.components.auth.register
import com.riveong.animalink.ui.components.detail.DetailProduct
import com.riveong.animalink.ui.components.home.header
import com.riveong.animalink.ui.components.home.headerFull
import com.riveong.animalink.ui.components.profile.User
import com.riveong.animalink.ui.components.reuseable.ListAnimalPage
import com.riveong.animalink.ui.components.reuseable.ListProductPage
import com.riveong.animalink.ui.model.animalsDummy
import com.riveong.animalink.ui.model.productDummy
import com.riveong.animalink.ui.theme.AnimalinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalinkTheme {
                //register = register()
                //login = login()
                //home = headerFull(username = "Jamal")
                //product list page =  ListProductPage(produk = productDummy)
                //ListAnimalPage(hewan = animalsDummy)
                //user = User()
                //detail product = DetailProduct()
                DetailProduct(title = "Ayam")

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimalinkTheme {
        headerFull(username = "Jamal")
    }
}