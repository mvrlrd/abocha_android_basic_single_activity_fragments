package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cupcake.model.OrderViewModel


@Composable
fun StartScreen(viewModel: OrderViewModel, goToFlavorScreen: ()->Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(top = 8.dp),
            contentScale = ContentScale.None
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyButton(
            text = stringResource(R.string.one_cupcake),
            quantity = integerResource(id = R.integer.one_cup)
        ) {
            viewModel.setQuantity(it)
            goToFlavorScreen.invoke()
        }
        MyButton(
            text = stringResource(R.string.six_cupcakes),
            quantity = integerResource(id = R.integer.six_cups)
        ) {
            viewModel.setQuantity(it)
            goToFlavorScreen.invoke()
        }
        MyButton(
            text = stringResource(R.string.twelve_cupcakes),
            quantity = integerResource(id = R.integer.twelve_cups)
        ) {
            viewModel.setQuantity(it)
            goToFlavorScreen.invoke()
        }
    }
}

@Composable
fun MyButton(text: String, quantity: Int, callback: (Int)->Unit){
    Button(
        onClick = { callback.invoke(quantity) },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .widthIn(min = 250.dp)
    ) {
        Text(text = text.uppercase())
    }
}
