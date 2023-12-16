package com.riveong.animalink.ui.components.reuseable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riveong.animalink.R
import com.riveong.animalink.ui.model.Product
import com.riveong.animalink.ui.model.productDummy

@Composable
fun ListProducts(
    listProduct: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(Modifier.padding(27.dp)) {

        Text(
            text = "Animal Products",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        //TODO: add categories

        //items
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(19.dp),
            verticalArrangement = Arrangement.spacedBy(19.dp),
            modifier = modifier
        ) {
            items(listProduct, key = { it.title }) { animol ->
                LatestProduct(animol)
                Spacer(modifier = Modifier.height(24.dp))

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun iforgor() {
    MaterialTheme {
        ListProducts(
            listProduct = productDummy,
            modifier = Modifier.padding(8.dp)
        )
    }
}
