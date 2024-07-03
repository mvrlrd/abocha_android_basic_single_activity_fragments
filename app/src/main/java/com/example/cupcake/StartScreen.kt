package com.example.cupcake

import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
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
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.cupcake.model.OrderViewModel


@Composable
fun StartScreen(viewModel: OrderViewModel, navHostController: NavHostController) {
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
                .padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.order_cupcakes),
            style = MaterialTheme.typography.h4,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyButton(text = stringResource(R.string.one_cupcake)) {
            viewModel.setQuantity(R.integer.one_cup)
            navHostController.navigate(Routes.FlavorScreen.route)
        }
        MyButton(text = stringResource(R.string.six_cupcakes)) {
            viewModel.setQuantity(R.integer.six_cups)
            navHostController.navigate(Routes.FlavorScreen.route)
        }
        MyButton(text = stringResource(R.string.twelve_cupcakes)) {
            viewModel.setQuantity(R.integer.twelve_cups)
            navHostController.navigate(Routes.FlavorScreen.route)
        }

    }
}

//fun orderCupcake(quantity: Int) {
//    // Update the view model with the quantity
//    sharedViewModel.setQuantity(quantity)
//
//    // If no flavor is set in the view model yet, select vanilla as default flavor
//    if (sharedViewModel.hasNoFlavorSet()) {
//        sharedViewModel.setFlavor(getString(R.string.vanilla))
//    }
//
//    // Navigate to the next destination to select the flavor of the cupcakes
//    findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
//}

@Composable
fun MyButton(text: String, callback: ()->Unit){
    Button(
        onClick = { callback.invoke() },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .widthIn(min = 250.dp)
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun StartScreenPreview() {
//    StartScreen(OrderViewModel(), )
}
