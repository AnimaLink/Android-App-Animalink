package com.riveong.animalink.ui.components.splash

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.datastore.UserStore
import com.riveong.animalink.data.model.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun splash(modifier: Modifier = Modifier): Boolean{
    val context = LocalContext.current
    val store = UserStore(context)
    val store2 = ProfileStore(context)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(R.drawable.logo_dummy),
            contentDescription = null,
            Modifier.width(169.dp)
        )

    }
//TODO: if login / if not login
    getInfo(context, store, store2)
    return false
}

@OptIn(DelicateCoroutinesApi::class)
private fun getInfo(context: Context, store: UserStore, profile: ProfileStore) {

    val uiScope = CoroutineScope(Dispatchers.Main)
    uiScope.launch {
        val token: LiveData<String> = store.getAccessToken.asLiveData()
        token.observeForever { data: String ->
            val client = ApiConfig.getApiService(data).getUserData()
            client.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null) {
                        //logic if successful

                        if (responseBody.status == "success") {
                            GlobalScope.launch {
                                profile.saveProfile(
                                    username = "${responseBody.data.userData.firstName} ${responseBody.data.userData.lastName}",
                                    email = responseBody.data.userData.email,
                                    avatar = responseBody.data.userData.avatar
                                )
                            }



                        }

                        if (responseBody.status == "fail") {
                            Toast.makeText(
                                context,
                                "Something went wrong: ${responseBody.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(context, "Something went wrong (┬┬﹏┬┬)", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
}

        @Composable
        @Preview
        fun splashTesting() {
            splash()

        }
