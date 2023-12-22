package com.riveong.animalink.ui.components.auth

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.navigation.NavHostController
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.model.RegisterResponse
import com.riveong.animalink.ui.screen.Screen
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun login(modifier: Modifier = Modifier, navHostController: NavHostController){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var message = "please check ur data, password must be 6 chars"

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
            text = "A Great Start!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
                )
        )

        Spacer(modifier = Modifier.height(21.dp))

        Text(
            text = "Please register to continue",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                )
        )

        Spacer(modifier = Modifier.height(14.dp))
        Row (
            modifier = modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                singleLine = true,
                shape = RoundedCornerShape(size = 13.dp),
                modifier = modifier.width(169.dp)

            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                singleLine = true,
                shape = RoundedCornerShape(size = 13.dp),
                modifier = modifier.width(169.dp)

            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))


        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

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

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "By registering and continuing you are agree to Animalink Term of Sevices and Privacy Policy.",
            style = TextStyle(
                fontSize = 12.sp
            )

        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                postRegister(context, email, firstName, lastName, password, phone, navHostController) { messsage ->
                    message = messsage
                }



            }
            ,


            modifier = modifier
                .align(CenterHorizontally)
                .width(267.dp)
                .height(58.dp)

        ) {
                Text(
                    text = "Register",
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(modifier.align(CenterHorizontally)) {

            Text(text = "Already have an account?")

            ClickableText(
                text = AnnotatedString(" Login instead!"),
                style = TextStyle(fontWeight = FontWeight.Bold),
                onClick = {
                    navHostController.navigate(Screen.Register.route)
                }


            )

        }
    }
}


@OptIn(DelicateCoroutinesApi::class)
fun postRegister(context: Context, email: String, fname: String, lname:String, password: String, phone: String, navHostController: NavHostController, onResult: (String) -> Unit) {
    val client = ApiConfig.getApiService("").postRegister(
        fname = fname, lname = lname, email = email, password = password, phone = phone
    )
    client.enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(
            call: Call<RegisterResponse>,
            response: Response<RegisterResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                if (responseBody.status == "success") {
                    Handler(Looper.getMainLooper()).post {
                        onResult("User created, please login to continue")
                        Toast.makeText(context, "User created, please login to continue", Toast.LENGTH_SHORT).show()
                        navHostController.navigate(Screen.Register.route)
                    }
                } else if (responseBody.status != "success") {
                    Handler(Looper.getMainLooper()).post {
                        onResult("failed, email already registered / illegal input")
                        Toast.makeText(context, "Check ur data!!!", Toast.LENGTH_SHORT).show()

                    }

                    }
                }
            }


        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Handler(Looper.getMainLooper()).post {
                onResult("Check your internet connection")
            }
        }
    })
}


/*
private fun postRegister(context: Context, email: String, fname: String, lname:String, password: String, phone: String, navHostController: NavHostController): Boolean? {
    var status: Boolean? = null
    val client = ApiConfig.getApiService("").postRegister(
        fname = fname, lname = lname, email = email, password = password, phone = phone
    )
    client.enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(
            call: Call<RegisterResponse>,
            response: Response<RegisterResponse>
        ) {

            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                //logic if successful

                if (responseBody.status == "success") {
                    Toast.makeText(
                        context,
                        "User created, please login to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                    navHostController.navigate(Screen.Register.route)
                    status = true

                }

                if (responseBody.status != "success") {
                    Toast.makeText(
                        context,
                        "Something went wrong: ${responseBody.message.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    status = false

                }
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Toast.makeText(context, "Something went wrong (┬┬﹏┬┬)", Toast.LENGTH_SHORT).show()
            status = false
        }

    })
    return status
}
        */