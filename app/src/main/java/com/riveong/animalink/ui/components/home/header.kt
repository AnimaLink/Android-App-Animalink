package com.riveong.animalink.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.riveong.animalink.Ihate
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.ForumResponse
import com.riveong.animalink.data.model.Product
import com.riveong.animalink.data.model.Profile
import com.riveong.animalink.ui.components.reuseable.LatestAnimalsRow
import com.riveong.animalink.ui.components.reuseable.LatestProductRow
import com.riveong.animalink.data.model.productDummy
import com.riveong.animalink.ui.components.additional.DetectLink
import com.riveong.animalink.ui.components.additional.InfoLink
import com.riveong.animalink.ui.components.additional.NewsLink
import com.riveong.animalink.ui.components.additional.VetLink
import com.riveong.animalink.ui.components.auth.login
import com.riveong.animalink.ui.components.auth.register
import com.riveong.animalink.ui.components.profile.getUserProfile
import com.riveong.animalink.ui.components.reuseable.News
import com.riveong.animalink.ui.components.splash.splash
import com.riveong.animalink.ui.screen.Screen
import com.riveong.animalink.ui.theme.primary
import com.riveong.animalink.ui.theme.secondary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun headerFull(modifier: Modifier = Modifier,username: String, navHostController: NavHostController,navigateToDetail: (Long) -> Unit,){
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(listOf(Animal(0,"https://static.wikia.nocookie.net/typemoon/images/7/71/Neco-Arc_Remake.png/revision/latest?cb=20210902002059","Neco arc","🗿 Car","Sold"))) }
    val userData = remember { mutableStateOf(Profile("","")) }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = store) {
        getAnimalData(store) { anima ->
            data.value = anima ?: listOf()

        }
    }

    LaunchedEffect(key1 = store) {
        getUserProfile(store) { profile ->
            userData.value = profile
        }
    }


Column(
    modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())

) {
    header(data = userData.value)
    banner()
    featureMenu(navHostController)
    LaunchedEffect(key1 = store) {
        getAnimalData(store) { anima ->
            data.value = anima ?: listOf()

        }
    }
    Spacer(modifier = Modifier.height(10.dp))

    LatestAnimalsRow(listAnimals = data.value, navHostController = navHostController, navigateToDetail = navigateToDetail)
    Spacer(modifier = Modifier.height(10.dp))
    News(
        animal = Product(R.drawable.cat, "Kucing ternyata bisa sembuhkan depresi! Siapa nih yang barusan tahu kucing bisa sembuhkan depresi? Yap apakah kalian pernah perhatikan...", "Kocheng!","type moon"),
    )

}
}

suspend fun getAnimalData(store: ProfileStore, callback: (List<Animal>?) -> Unit) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }
    val client = ApiConfig.getApiService(ttoken).getForum()
    client.enqueue(object : Callback<ForumResponse> {
        override fun onResponse(
            call: Call<ForumResponse>,
            response: Response<ForumResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful
                if (responseBody.status == "success") {
                    val animalList = responseBody.data?.listForum?.map {
                        Animal(it!!.id!!,it!!.imgUrl!!, it!!.name!!, it!!.category!!.name!!,it!!.status!!.name!!)
                    }
                    callback(animalList)
                }
                if (responseBody.status == "fail") {
                    //TODO
                }
            }
        }

        override fun onFailure(call: Call<ForumResponse>, t: Throwable) {
             print("no data")
        }
    })
}

@Composable
fun header(data: Profile, modifier: Modifier = Modifier){

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(27.dp)
    ){

        AsyncImage(
            model = data.avatar,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(25.dp)
        )

        Spacer(
            modifier = Modifier.width(9.dp)
        )

        Text(
            style = TextStyle(
                fontWeight = FontWeight(700),
                fontSize = 15.sp

            ),
            text = "Welcome "
        )
        Text(
            style = TextStyle(
                color = Color(0xFFF47D25),
                fontWeight = FontWeight(700),
                fontSize = 15.sp
            ),
            text = data.username!!
        )

        Spacer(
            Modifier.weight(1f)

        )

        Box(
            Modifier
                .width(75.dp)
                .height(23.dp)
                .background(color = primary, shape = RoundedCornerShape(size = 16.5.dp)),
            contentAlignment = Alignment.Center,

            ){
            Text(
                text = "Member",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
            )
        }

    }
}

@Composable
fun banner(modifier: Modifier = Modifier){
Column(
    Modifier
        .fillMaxWidth()
        .padding(horizontal = 27.dp)) {
        Image(
            painterResource(R.drawable.banner),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(356.dp)
                .height(158.dp)
                .background(
                    color = primary, shape = RoundedCornerShape(24.dp),)
        )
}
        Spacer(modifier = Modifier.height(28.dp))
    }





@Composable
fun featureMenu(navHostController: NavHostController,modifier: Modifier = Modifier) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp)) {


        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)

        ){
            //1 VetLink
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.clickable {

                        navHostController.navigate(Screen.VetLink.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }


                    }
                        .width(62.dp)
                        .height(62.dp)
                        .background(color = secondary, shape = RoundedCornerShape(size = 15.dp)


                        )
                ) {
                    Image(
                        painterResource(R.drawable.vet),
                        null,
                        Modifier.width(35.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                        )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "VetLink",
                    textAlign = TextAlign.Center,
                    color = Color.Black,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),
                    )
                )
            }

            //2
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.clickable {

                        navHostController.navigate(Screen.DetectLink.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }


                    }

                        .width(62.dp)
                        .height(62.dp)
                        .background(
                            color = secondary, shape = RoundedCornerShape(size = 15.dp)
                        )
                ) {
                    Image(
                        painterResource(R.drawable.scan),
                        null,
                        Modifier.width(35.dp),
                        colorFilter = ColorFilter.tint(Color.White)

                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "DetectLink",
                    textAlign = TextAlign.Center,
                    color = Color.Black,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),
                        )
                )
            }

            //3
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.clickable {

                        navHostController.navigate(Screen.NewsLink.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }


                    }

                        .width(62.dp)
                        .height(62.dp)
                        .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
                ) {
                    Image(
                        painterResource(R.drawable.news),
                        null,
                        Modifier.width(35.dp),
                        colorFilter = ColorFilter.tint(Color.White)

                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "NewsLink",
                    textAlign = TextAlign.Center,
                    color = Color.Black,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),

                        )
                )
            }

            //4
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.clickable {

                        navHostController.navigate(Screen.InfoLink.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }


                    }

                        .width(62.dp)
                        .height(62.dp)
                        .background(color = secondary, shape = RoundedCornerShape(size = 15.dp))
                ) {
                    Image(
                        painterResource(R.drawable.info),
                        null,
                        Modifier.width(35.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "InfoLink",
                    textAlign = TextAlign.Center,
                    color = Color.Black,

                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),

                        )
                )
            }


        }
    }
}
