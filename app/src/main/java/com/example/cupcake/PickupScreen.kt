package com.example.cupcake

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.RadioButton

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.cupcake.model.OrderViewModel

@Composable
fun PickupScreen(viewModel: OrderViewModel, navController: NavController?) {
    val dateOptions = viewModel.dateOptions
    val selectedDate = viewModel.date
    val subtotal = viewModel.price

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.side_margin))
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.side_margin))
        ) {
            dateOptions.forEachIndexed { index, date ->
                RadioButtonWithLabel(selectedDate = selectedDate, date = date, viewModel = viewModel, label = date)
            }
        }

        Divider(
            color = MaterialTheme.colors.background,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.side_margin))
        )

        Text(
            text = stringResource(R.string.subtotal_price, subtotal),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.side_margin))
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.side_margin)))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { navController?.navigate("cancel") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.cancel))
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.side_margin)))

            Button(
                onClick = { navController?.navigate("next") },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

//TODO выровнить лэйбл и радиобаттон
@Composable
fun RadioButtonWithLabel(selectedDate: LiveData<String>, date: String, viewModel: OrderViewModel, label: String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = selectedDate.value == date,
            onClick = { viewModel.setDate(date) },
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = label)
    }
}
@Composable
@Preview
fun PreviewPickupScreen(){
    PickupScreen(viewModel = OrderViewModel(), navController = null)
}
