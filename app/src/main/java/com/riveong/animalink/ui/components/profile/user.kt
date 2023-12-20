package com.riveong.animalink.ui.components.profile

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Data
import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.data.model.Profile
import com.riveong.animalink.data.model.ProfileDummy
import com.riveong.animalink.data.model.UserResponse
import com.riveong.animalink.data.model.animalsDummy
import com.riveong.animalink.data.model.productDummy
import com.riveong.animalink.ui.components.reuseable.LatestAnimalsRow
import com.riveong.animalink.ui.components.reuseable.LatestProductRow
import com.riveong.animalink.ui.theme.primary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun settingBar(modifier: Modifier = Modifier){

    Row (
        modifier
            .fillMaxWidth()
            .padding(27.dp),
        horizontalArrangement = Arrangement.End
    ){


        Image(
            imageVector = ImageVector.vectorResource(R.drawable.settings),
            contentDescription = "setting button",
        )
        Spacer(modifier.width(12.dp))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.logout),
            contentDescription = "logout button",
        )

    }

}

@Composable
//TODO: make data class to put the info
fun UserInfo(modifier: Modifier = Modifier, profile: Profile){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = profile.avatar,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = profile.username!!,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                )


        )
        Spacer(modifier = Modifier.height(9.dp))
        Box(
            Modifier
                .width(108.dp)
                .height(33.dp)
                .background(color = primary, shape = RoundedCornerShape(size = 16.5.dp)),
            contentAlignment = Alignment.Center,

            ){
            Text(
                text = "Member",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    )
            )
        }


    }


}

//TODO: FIX THIS
@Composable
fun User() {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    val data = remember { mutableStateOf(Profile("","")) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = store) {
        getUserProfile(store) { profile ->
            data.value = profile
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        settingBar()
        UserInfo(profile = data.value)
        LatestAnimalsRow(listAnimals = animalsDummy, "My Animal Listing")
        LatestProductRow(listProduct = productDummy, "My Product Listing")
    }
}

suspend fun getUserProfile(store: ProfileStore, callback: (Profile) -> Unit) {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    token.observeForever{data:String ->
        ttoken = data
    }
    val client = ApiConfig.getApiService(ttoken).getUserData()
    client.enqueue(object : Callback<UserResponse> {
        override fun onResponse(
            call: Call<UserResponse>,
            response: Response<UserResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful
                if (responseBody.status == "success") {
                    val userP = Profile(responseBody.data.userData.first_name, responseBody.data.userData.avatar)
                    callback(userP)
                }
                if (responseBody.status == "fail") {
                    //TODO
                }
            }
        }
        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            //TODO
        }
    })
}


/*fun getUserProfile(store: ProfileStore): Profile {

    var userP : Profile = Profile("bruh","")
    var ttoken = ""
    GlobalScope.launch(Dispatchers.Main) {
        val token: LiveData<String> = store.getTokenProfile.asLiveData()
        token.observeForever{data:String ->
            ttoken = data
        }
        val client = ApiConfig.getApiService(ttoken).getUserData()
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {

                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    //logic if successful

                    if (responseBody.status == "success") {
                        userP = Profile("${responseBody.data.userData.firstName} ${responseBody.data.userData.lastName}", responseBody.data.userData.avatar)
                    }

                    if (responseBody.status == "fail") {
                        //TODO
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                //TODO
            }

        })
    }
    return userP
}*/


