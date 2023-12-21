package com.riveong.animalink.ui.components.forum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.AnimalExtended
import com.riveong.animalink.data.model.Profile
import com.riveong.animalink.data.model.SingleForumResponse
import com.riveong.animalink.data.model.UserResponse
import com.riveong.animalink.ui.components.profile.getUserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun DetailProduct(modifier: Modifier = Modifier, id: Long){
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(AnimalExtended(0,"","","","","","")) }

    LaunchedEffect(key1 = store) {
        getDetail(id = id.toInt(),store = store) { da ->
            data.value = da
        }
    }

    Column(Modifier
        .fillMaxWidth()
    ) {
        AsyncImage(
            model = data.value.image,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            contentDescription = "item picture",
            contentScale = ContentScale.FillBounds
            )
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 700.dp)
                .offset(y = -24.dp)

                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(
                        topStart = 27.dp,
                        topEnd = 27.dp
                    )
                )
        ){
            Column(modifier = Modifier.padding(top = 33.dp,start = 33.dp,end = 33.dp)) {
                Text(text = data.value.title,
                    style = TextStyle(
                        fontSize = 32.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    )
                )
                Text(text = "IDR ${data.value.price}",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color(0xFF000000),
                        )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = data.value.description)
                Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Order")
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = { /*TODO*/ }) {
                            Text(text = "Chat Seller")

                        }
                    
                    }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Comments",
                style = TextStyle(
                        fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                    )
                )
            }



        }
    }

}

suspend fun getDetail(id:Int, store: ProfileStore, callback: (AnimalExtended) -> Unit) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }
    val client = ApiConfig.getApiService(ttoken).getSingleForum(id.toString())
    client.enqueue(object : Callback<SingleForumResponse> {
        override fun onResponse(
            call: Call<SingleForumResponse>,
            response: Response<SingleForumResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful
                if (responseBody.status == "success") {
                    val userP = AnimalExtended(
                        id = responseBody!!.data!!.forum!!.id!!,
                        Status = responseBody!!.data!!.forum!!.status!!.name!!,
                        species = responseBody!!.data!!.forum!!.category!!.name!!,
                        title = responseBody!!.data!!.forum!!.name!!,
                        image = responseBody!!.data!!.forum!!.imgUrl!!,
                        price = responseBody!!.data!!.forum!!.price!!,
                        description = responseBody!!.data!!.forum!!.description!!
                    )
                    callback(userP)
                }
                if (responseBody.status == "fail") {
                    //TODO
                }
            }
        }


        override fun onFailure(call: Call<SingleForumResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}
