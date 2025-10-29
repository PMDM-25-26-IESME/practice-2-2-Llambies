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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrian.practica22.R
import com.adrian.practica22.ui.viewmodels.VatViewModel


@Composable
fun VatScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<VatViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val priceValue = uiState.price.toFloatOrNull() ?: 0f
    val vatValue = uiState.vat.toFloatOrNull() ?: 0f
    val totalPrice = priceValue * (1 + vatValue / 100)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Calculadora de IVA", style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = uiState.name,
            onValueChanged = { viewModel.updateName(it) },
            label = R.string.product_name,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = uiState.price,
            onValueChanged = { viewModel.updatePrice(it) },
            label = R.string.price,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = uiState.vat,
            onValueChanged = { viewModel.updateVat(it) },
            label = R.string.vat,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Text(
            text = "Precio total: %.2f €".format(totalPrice),
            style = MaterialTheme.typography.displayMedium
        )
    }
}
