package com.riveong.animalink.ui.components.forum

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.AnimalExtended
import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.data.model.PostCommentResponse
import com.riveong.animalink.data.model.Profile
import com.riveong.animalink.data.model.SingleForumResponse
import com.riveong.animalink.data.model.UserResponse
import com.riveong.animalink.ui.components.profile.getUserProfile
import com.riveong.animalink.ui.screen.Screen
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun DetailProduct(modifier: Modifier = Modifier, id: Long){
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(AnimalExtended(0,"","","","","","")) }
    var comment by remember { mutableStateOf("") }


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
                .height(240.dp),
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
            Column(modifier = Modifier
                .padding(top = 33.dp,start = 33.dp,end = 33.dp)){
                Text(text = data.value.title,
                    style = TextStyle(
                        fontSize = 22.sp,
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
                Spacer(modifier = Modifier.height(15.dp))


                Text(text = "Comments",
                style = TextStyle(
                        fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                    )
                )

                OutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text(
                        "add comment"
                    )
                            },
                    singleLine = true,
                    shape = RoundedCornerShape(size = 13.dp),
                    modifier = modifier
                        .fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {

                    if (data.value.id != 0) {
                        postComment(id = data.value.id, context, comment, store)
                    }



                }) {
                    Text(text = "post comment")
                }

                Spacer(modifier = Modifier.height(20.dp))



                if (data.value.id != 0) {
                    commentsList(data.value.id)
                }


            }



        }
    }

}

@OptIn(DelicateCoroutinesApi::class)
private fun postComment(id: Int, context: Context, Comment: String, store: ProfileStore, /*navHostController: NavHostController*/) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }

    val client = ApiConfig.getApiService(ttoken).postComment(
        id = id.toString(), comment = Comment
    )
    client.enqueue(object : Callback<PostCommentResponse> {
        override fun onResponse(
            call: Call<PostCommentResponse>,
            response: Response<PostCommentResponse>
        ) {

            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful

                if (responseBody.status == "success") {
                    Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                    }
                }

                if (responseBody?.status == "fail") {
                    Toast.makeText(context, "Something went wrong: ${responseBody.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            }

        override fun onFailure(call: Call<PostCommentResponse>, t: Throwable) {
            Toast.makeText(context, "Check ur internet", Toast.LENGTH_SHORT).show()
        }
    })

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
                        id = responseBody.data!!.forum!!.id!!,
                        Status = responseBody.data.forum!!.status!!.name!!,
                        species = responseBody.data.forum.category!!.name!!,
                        title = responseBody.data.forum.name!!,
                        image = responseBody.data.forum.imgUrl!!,
                        price = responseBody.data.forum.price!!,
                        description = responseBody.data.forum.description!!
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
