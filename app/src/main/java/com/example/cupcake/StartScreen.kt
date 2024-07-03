package com.example.cupcake

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.model.OrderViewModel


@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier.size(200.dp) // Замените это значение на ваш размер изображения
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyButton(text = stringResource(R.string.one_cupcake)) {

        }
        MyButton(text = stringResource(R.string.six_cupcakes)) {

        }
        MyButton(text = stringResource(R.string.twelve_cupcakes)) {

        }

    }
}

@Composable
fun MyButton(text: String, callback: ()->Unit){
    Button(
        onClick = { callback.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen()
}
