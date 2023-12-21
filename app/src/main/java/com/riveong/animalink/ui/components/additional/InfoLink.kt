package com.riveong.animalink.ui.components.additional

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.BehaviourResponse
import com.riveong.animalink.data.model.ForumResponse
import com.riveong.animalink.data.model.ListBehaviourAnimalItem
import com.riveong.animalink.ui.components.home.getAnimalData
import com.riveong.animalink.ui.components.reuseable.LatestAnimal
import com.riveong.animalink.ui.theme.primary
import com.riveong.animalink.ui.theme.secondary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showSystemUi = true)
@Composable
fun InfoLink(
    modifier: Modifier = Modifier,

) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(listOf(ListBehaviourAnimalItem("","",""))) }
    Column(Modifier.padding(27.dp)) {

        Text(
            text = "InfoLink",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        LaunchedEffect(key1 = store) {
            getAnuData(store) { anima ->
                data.value = anima ?: listOf()

            }
        }
        LazyColumn(modifier = modifier) {
            items(data.value, key = { it.name!! }) { animol ->
                InfoList(animol)
            }

        }



    }
}

fun getAnuData(store: ProfileStore, callback: (List<ListBehaviourAnimalItem>?) -> Unit) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }
    val client = ApiConfig.getApiService(ttoken).getAllAnimals()
    client.enqueue(object : Callback<BehaviourResponse> {
        override fun onResponse(
            call: Call<BehaviourResponse>,
            response: Response<BehaviourResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful
                if (responseBody.status == "success") {
                    val animalList = responseBody.data?.listAnimal?.map {
                        ListBehaviourAnimalItem(it!!.imgUrl!!,it!!.name!!,it!!.behaviour!!)
                    }
                    callback(animalList)
                }
                if (responseBody.status == "fail") {
                    //TODO
                }
            }
        }


        override fun onFailure(call: Call<BehaviourResponse>, t: Throwable) {
            print("no data")
        }
    })
}



@Composable
fun InfoList(animal:ListBehaviourAnimalItem = ListBehaviourAnimalItem("","a","kejam"), modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxWidth()) {
        Card (
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ){
            Row {
                AsyncImage(
                    model = animal.imgUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                    text = animal.name!!,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if(animal.behaviour!! == "Jahat"){
                        Box(
                            Modifier
                                .width(75.dp)
                                .height(23.dp)
                                .background(color = secondary, shape = RoundedCornerShape(size = 16.5.dp)),
                            contentAlignment = Alignment.Center,

                            ){
                            Text(
                                text = animal.behaviour!!,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                    }
                    else {
                        Box(
                            Modifier
                                .width(75.dp)
                                .height(23.dp)
                                .background(
                                    color = primary,
                                    shape = RoundedCornerShape(size = 16.5.dp)
                                ),
                            contentAlignment = Alignment.Center,

                            ) {
                            Text(
                                text = animal.behaviour!!,
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
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}
