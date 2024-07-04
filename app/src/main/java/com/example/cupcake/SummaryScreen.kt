package com.example.cupcake

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(
    sharedViewModel: OrderViewModel,
    context: Context,
    onCancelOrder: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val quantity by sharedViewModel.quantity.observeAsState()
    val flavor by sharedViewModel.flavor.observeAsState()
    val date by sharedViewModel.date.observeAsState()
    val price by sharedViewModel.price.observeAsState()

    val numberOfCupcakes = sharedViewModel.quantity.value ?: 0
    val orderSummary = stringResource(
        R.string.order_details,
        pluralStringResource(id =R.plurals.cupcakes , count = numberOfCupcakes),
        sharedViewModel.flavor.value.toString(),
        sharedViewModel.date.value.toString(),
        sharedViewModel.price.value.toString()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
//                .focusable(true)
        ) {
            Text(
                text = "Quantity",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = "Flavor",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = flavor.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = "Pickup Date",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = date.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = "Total",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.End
            )
            Text(
                text = price.toString(),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.End
            )
        }

        Button(
            onClick = {
                      sendOrder(context = context, orderSummary=orderSummary)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Send")
        }

        OutlinedButton(
            onClick = onCancelOrder,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Cancel")
        }
    }
}

private fun sendOrder(context: Context, orderSummary: String) {
    // Construct the order summary text with information from the view model
    Log.d("TAG","sendOrder = $orderSummary")
    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT,  context.resources.getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)

    // Check if there's an app that can handle this intent before launching it
    if (context.packageManager?.resolveActivity(intent, 0) != null) {
        // Start a new activity with the given intent (this may open the share dialog on a
        // device if multiple apps can handle this intent)
        context.startActivity(intent)
    }
}

@Preview
@Composable
fun SummaryPreview(){
//    SummaryScreen(onSendOrder = { /*TODO*/ }) {
//        println()
//    }
}
