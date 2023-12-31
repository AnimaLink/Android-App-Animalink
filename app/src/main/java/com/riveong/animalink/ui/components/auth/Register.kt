package com.riveong.animalink.ui.components.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.LoginResponse
import com.riveong.animalink.ui.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun register(modifier: Modifier = Modifier, navHostController: NavHostController){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }


    Column(
        modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_dummy),
            contentDescription = "logo",
            modifier = Modifier
                .width(37.dp)
                .height(37.dp)
        )

        Text(
            text = "Welcome Back!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(21.dp))

        Text(
            text = "Let’s continue our journey!",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                postLogin(context, email, password, store, navHostController = navHostController) },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .width(267.dp)
                .height(58.dp)

        ) {
            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(modifier.align(Alignment.CenterHorizontally)) {

            Text(text = "Don't have an account?")

            ClickableText(
                text = AnnotatedString(" Register instead!"),
                style = TextStyle(fontWeight = FontWeight.Bold),
                onClick = {
                    navHostController.navigate(Screen.Login.route)
                }


            )

        }





    }
}


@OptIn(DelicateCoroutinesApi::class)
private fun postLogin(context: Context, email: String, password: String, store: ProfileStore, navHostController: NavHostController) {

    val client = ApiConfig.getApiService("").postLogin(
        email = email, password = password
    )
    client.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(
            call: Call<LoginResponse>,
            response: Response<LoginResponse>
        ) {

            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful

                if (responseBody.status == "success") {
                    GlobalScope.launch(Dispatchers.IO) {
                        store.saveProfile(token = responseBody.data?.accessToken!!)
                        withContext(Dispatchers.Main) {
                            navHostController.navigate(Screen.Splash.route)
                        }
                    }
                    Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()

                }

                else if (responseBody.status != "success") {
                    Toast.makeText(context, "Something went wrong: ${responseBody.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            Toast.makeText(context, "Something went wrong (┬┬﹏┬┬)", Toast.LENGTH_SHORT).show()
        }

    })
}

