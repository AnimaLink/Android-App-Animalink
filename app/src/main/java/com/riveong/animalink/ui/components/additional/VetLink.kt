package com.riveong.animalink.ui.components.additional

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.riveong.animalink.data.model.ListBehaviourAnimalItem

@Composable
fun VetLink(
    modifier: Modifier = Modifier,
) {
    Column(Modifier.padding(27.dp)) {

        Text(
            text = "VetLink",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Nearest Vet",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight(500),
            )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(modifier = modifier) {
                items(DummyVet, key = { it.name!! }) { animol ->
                    InfoVet(animol)
                }

            }
            Column(verticalArrangement = Arrangement.Bottom) {

                Text(
                    text = "Services",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight(500),
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(305.dp)
                    .height(44.dp)


            ) {

                Text(
                    text = "Online check up",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .width(305.dp)
                        .height(44.dp)


                ) {
                    Text(
                        text = "Emergency",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
    }
}

val DummyVet =  listOf(
    ListBehaviourAnimalItem("https://cdn.discordapp.com/attachments/1023598916857499680/1187421906891190452/image.png?ex=6596d3d3&is=65845ed3&hm=572859a64e670a5ce353eabf2443537795a1abb95ea629a90179bd76468ada99&","Azhar Vet","⭐⭐⭐⭐⭐\n Jl. Kabupaten, Kronggahan, Trihanggo, Kec. Gamping, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55291",1),
    ListBehaviourAnimalItem("https://media.discordapp.net/attachments/1023598916857499680/1187422027561320528/image.png?ex=6596d3f0&is=65845ef0&hm=8e1d638065976cc8a8a46eab21e7d35ce22bb2b939e9b8979937fe8bc651b594&=&format=webp&quality=lossless","Animalova","⭐⭐⭐⭐⭐\n Jl. Baru Mulungan, Gilingan, Sendangadi, Kec. Mlati, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55285",2),
)