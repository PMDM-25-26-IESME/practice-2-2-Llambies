package com.adrian.practica22.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adrian.practica22.R
import com.adrian.practica22.ui.viewmodels.VatViewModel

@Composable
fun VatScreen(
    modifier: Modifier = Modifier,
    viewModel: VatViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    StartScreen(
        name = uiState.name,
        price = uiState.price,
        vat = uiState.vat,
        onNameChanged = { viewModel.updateName(it) },
        onPriceChanged = { viewModel.updatePrice(it) },
        onVatChanged = { viewModel.updateVat(it) },
        onNavigateToResult = {},
        modifier = modifier
    )
}

@Composable
fun StartScreen(
    name: String,
    price: String,
    vat: String,
    onNameChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onVatChanged: (String) -> Unit,
    onNavigateToResult: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.product_name),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        EditField(
            value = name,
            onValueChanged = onNameChanged,
            label = R.string.product_name,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        EditField(
            value = price,
            onValueChanged = onPriceChanged,
            label = R.string.price,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        EditField(
            value = vat,
            onValueChanged = onVatChanged,
            label = R.string.vat,
            leadingIcon = R.drawable.ic_launcher_foreground,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Button(
            onClick = onNavigateToResult,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.view_total_price))
        }
    }
}
