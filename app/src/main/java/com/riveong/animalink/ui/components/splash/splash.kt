package com.riveong.animalink.ui.components.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.riveong.animalink.R
import com.riveong.animalink.ui.components.home.banner
import com.riveong.animalink.ui.components.home.featureMenu
import com.riveong.animalink.ui.components.home.header
import com.riveong.animalink.ui.components.home.headerFull
import com.riveong.animalink.ui.components.reuseable.LatestAnimalsRow
import com.riveong.animalink.ui.components.reuseable.LatestProductRow
import com.riveong.animalink.data.model.animalsDummy
import com.riveong.animalink.data.model.productDummy
import com.riveong.animalink.ui.theme.primary

@Composable
fun splash(modifier: Modifier = Modifier){
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

}

@Composable
@Preview
fun splashTesting(){
    splash()

}