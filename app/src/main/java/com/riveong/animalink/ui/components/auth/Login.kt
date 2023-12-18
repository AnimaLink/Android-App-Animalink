package com.riveong.animalink.ui.components.auth

import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riveong.animalink.R
import com.riveong.animalink.data.api.ApiConfig
import com.riveong.animalink.data.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun login(modifier: Modifier = Modifier){
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {

        Spacer(modifier = Modifier.height(84.dp))

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
                postRegister(context = context, email = email, fname = firstName, lname = lastName, password = password, phone = phone)
            },
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

            Text(
                text = " Login instead!",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

        }





    }
}



private fun postRegister(context: Context, email: String, fname: String, lname:String, password: String, phone: String) {

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
                    Toast.makeText(context, "${responseBody.message.toString()}, please login to continue", Toast.LENGTH_SHORT).show()


                }

                if (responseBody.status == "fail") {
                    Toast.makeText(context, "Something went wrong: ${responseBody.message.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Toast.makeText(context, "Something went wrong (┬┬﹏┬┬)", Toast.LENGTH_SHORT).show()
        }

    })
}


@Composable
@Preview(showBackground = true)
fun testlogin(){
    login()
}