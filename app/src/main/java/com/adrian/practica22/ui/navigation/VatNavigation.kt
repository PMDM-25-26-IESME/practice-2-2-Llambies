package com.adrian.practica22.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adrian.practica22.ui.ResultScreen
import com.adrian.practica22.ui.StartScreen
import com.adrian.practica22.ui.viewmodels.VatViewModel

sealed class VatScreen(val route: String) {
    object Start : VatScreen("start")
    object Result : VatScreen("result/{name}/{price}/{vat}") {
        fun createRoute(name: String, price: String, vat: String): String {
            return "result/${Uri.encode(name)}/$price/$vat"
        }
    }
}

@Composable
fun VatNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: VatViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = VatScreen.Start.route,
        modifier = modifier
    ) {
        composable(VatScreen.Start.route) {
            StartScreen(
                name = uiState.name,
                price = uiState.price,
                vat = uiState.vat,
                onNameChanged = { viewModel.updateName(it) },
                onPriceChanged = { viewModel.updatePrice(it) },
                onVatChanged = { viewModel.updateVat(it) },
                onNavigateToResult = {
                    navController.navigate(
                        VatScreen.Result.createRoute(
                            uiState.name,
                            uiState.price,
                            uiState.vat
                        )
                    )
                }
            )
        }

        composable(
            route = VatScreen.Result.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("vat") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val priceStr = backStackEntry.arguments?.getString("price") ?: "0"
            val vatStr = backStackEntry.arguments?.getString("vat") ?: "0"
            
            val price = priceStr.toFloatOrNull() ?: 0f
            val vatPercentage = vatStr.toFloatOrNull() ?: 0f
            val vatAmount = price * (vatPercentage / 100)
            val totalAmount = price + vatAmount

            ResultScreen(
                productName = name,
                priceWithoutVat = price,
                vatAmount = vatAmount,
                totalAmount = totalAmount,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
