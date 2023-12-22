package com.riveong.animalink.ui.components.forum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Animal
import com.riveong.animalink.data.model.AnimalExtended
import com.riveong.animalink.data.model.CommentResponse
import com.riveong.animalink.data.model.GetCommentNew
import com.riveong.animalink.data.model.ListCommentItem
import com.riveong.animalink.data.model.NewListCommentItem
import com.riveong.animalink.data.model.Profile
import com.riveong.animalink.data.model.SingleForumResponse
import com.riveong.animalink.ui.components.profile.getUserProfile
import com.riveong.animalink.ui.components.reuseable.LatestAnimal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun commentsList(forumID: Int) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(listOf(NewListCommentItem("not Found",1))) }
    val data2 = remember { mutableStateOf(Profile("","")) }

    LaunchedEffect(key1 = store) {
        getComment(id = forumID,store = store) { da ->
            data.value = da
        }
    }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(data.value, key = { it.id!! }) { animol ->
                comments(komen = animol)
            }
        }


}


@Composable
fun comments(komen:NewListCommentItem, modifier: Modifier = Modifier){
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        )  {
            AsyncImage(
                model = komen.user?.avatar ?: "https://enoughproject.org/wp-content/uploads/2017/04/Ryan_Gosling-e1493121669188-300x300.jpg",
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = komen.user?.name ?: "Unknown",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color(0xFF000000),
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = komen.comment)
            }
        }

    }
}



suspend fun getComment(id:Int, store: ProfileStore, callback: (List<NewListCommentItem>) -> Unit) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }
    val client = ApiConfig.getApiService(ttoken).getComment(id.toString())
    client.enqueue(object : Callback<GetCommentNew> {
        override fun onResponse(
            call: Call<GetCommentNew>,
            response: Response<GetCommentNew>
        ) {
            val responseBody = response.body()
            if (responseBody!!.status == "success") {

                val userP = responseBody.data?.listComment
                if (userP != null) {
                    callback(userP)
                }
            } else { print("something ain't right lil bro")
            }

        }


        override fun onFailure(call: Call<GetCommentNew>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}