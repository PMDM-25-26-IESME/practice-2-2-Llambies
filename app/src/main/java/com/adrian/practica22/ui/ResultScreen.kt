package com.adrian.practica22.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adrian.practica22.R

@Composable
fun ResultScreen(
    productName: String,
    priceWithoutVat: Float,
    vatAmount: Float,
    totalAmount: Float,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.product_label, productName),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Text(
            text = stringResource(R.string.price_without_vat, String.format("€%.2f", priceWithoutVat)),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
        Text(
            text = stringResource(R.string.vat_amount, String.format("€%.2f", vatAmount)),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Text(
            text = stringResource(R.string.total_amount, String.format("€%.2f", totalAmount)),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.back))
        }
    }
}
