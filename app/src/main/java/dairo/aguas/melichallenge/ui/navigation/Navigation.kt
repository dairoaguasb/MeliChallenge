package dairo.aguas.melichallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dairo.aguas.melichallenge.ui.detail.DetailScreen
import dairo.aguas.melichallenge.ui.product.ProductListScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.PRODUCT.route
    ) {
        productNav(navController)
    }
}

fun NavGraphBuilder.productNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.PRODUCT).route,
        route = Feature.PRODUCT.route
    ) {
        composable(NavCommand.ContentType(Feature.PRODUCT)) {
            ProductListScreen(
                viewModel = hiltViewModel()
            ) { id ->
                navController.navigate(
                    NavCommand.ContentTypeDetail(Feature.PRODUCT).createRoute(id)
                )
            }
        }
        composable(NavCommand.ContentTypeDetail(Feature.PRODUCT)) { backStackEntry ->
            val id = requireNotNull(backStackEntry.arguments?.getString(NavArg.ItemId.key))
            DetailScreen(productId = id)
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}
