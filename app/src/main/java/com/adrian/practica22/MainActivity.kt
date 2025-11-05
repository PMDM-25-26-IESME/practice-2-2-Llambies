package com.adrian.practica22

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adrian.practica22.ui.navigation.VatNavHost
import com.adrian.practica22.ui.navigation.VatScreen
import com.adrian.practica22.ui.theme.Practica23Theme
import com.adrian.practica22.ui.viewmodels.VatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica23Theme {
                VatCalculatorApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VatCalculatorApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val viewModel: VatViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (currentRoute == VatScreen.Start.route) {
                            Image(
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.image_size))
                                    .padding(dimensionResource(id = R.dimen.padding_small)),
                                painter = painterResource(R.drawable.ic_launcher_foreground),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = if (currentRoute == VatScreen.Start.route) 
                                stringResource(R.string.app_name) 
                            else 
                                stringResource(R.string.product_total_price),
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                },
                navigationIcon = {
                    if (currentRoute?.startsWith("result") == true) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                actions = {
                    if (currentRoute?.startsWith("result") == true) {
                        IconButton(onClick = {
                            val name = currentBackStackEntry?.arguments?.getString("name") ?: ""
                            val priceStr = currentBackStackEntry?.arguments?.getString("price") ?: "0"
                            val vatStr = currentBackStackEntry?.arguments?.getString("vat") ?: "0"
                            
                            val price = priceStr.toFloatOrNull() ?: 0f
                            val vatPercentage = vatStr.toFloatOrNull() ?: 0f
                            val totalAmount = price * (1 + vatPercentage / 100)

                            val shareText = """
                                Product: $name
                                Price without VAT: $${String.format("%.2f", price)}
                                Total amount: $${String.format("%.2f", totalAmount)}
                            """.trimIndent()

                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = stringResource(R.string.share)
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        VatNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}
