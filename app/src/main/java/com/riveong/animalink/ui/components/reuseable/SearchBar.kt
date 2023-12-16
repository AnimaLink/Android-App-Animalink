package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.riveong.animalink.R

@Composable
fun searchBar(modifier: Modifier = Modifier){

    Row (
        modifier
            .fillMaxWidth()
            .padding(27.dp),
        horizontalArrangement = Arrangement.End
    ){


        Image(
            imageVector = ImageVector.vectorResource(R.drawable.search),
            contentDescription = "search button",
        )

    }

}