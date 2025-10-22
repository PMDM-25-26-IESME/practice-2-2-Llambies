package com.adrian.practica22.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adrian.practica22.R


@Composable
fun VatScreen(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var vat by rememberSaveable { mutableStateOf("") }

    val priceValue = price.toFloatOrNull() ?: 0f
    val vatValue = vat.toFloatOrNull() ?: 0f
    val totalPrice = priceValue * (1 + vatValue / 100)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Product", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = name,
            onValueChanged = { name = it },
            label = R.string.product_name,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = price,
            onValueChanged = { price = it },
            label = R.string.price,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = vat,
            onValueChanged = { vat = it },
            label = R.string.vat,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Text(
            text = "Total price: %.2f €".format(totalPrice),
            style = MaterialTheme.typography.displayMedium
        )
    }
}
