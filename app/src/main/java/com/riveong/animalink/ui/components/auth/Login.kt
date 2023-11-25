package com.riveong.animalink.ui.components.auth

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riveong.animalink.R

@Composable
fun login(modifier: Modifier = Modifier){

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

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Username") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "By registering and continuing you are agree to Animalink Term of Sevices and Privacy Policy.",
            style = TextStyle(
                fontSize = 12.sp
            )

        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = { /*TODO*/ },
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


@Composable
@Preview(showBackground = true)
fun test(){
    login()
}