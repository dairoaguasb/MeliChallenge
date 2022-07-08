package dairo.aguas.melichallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dairo.aguas.melichallenge.ui.common.EmptyState
import dairo.aguas.melichallenge.ui.common.SearchLayout
import dairo.aguas.melichallenge.ui.navigation.Navigation
import dairo.aguas.melichallenge.ui.product.ProductListScreen
import dairo.aguas.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    MeliChallengeScreen {
        Navigation(navController = navController)
    }
}

@Composable
fun HomeApp(openDetail: (String) -> Unit) {
    var searchProduct by remember { mutableStateOf("") }
    var searchText by remember { mutableStateOf("") }
    Column {
        SearchLayout(
            value = searchText,
            onValueChange = { searchText = it }
        ) {
            searchProduct = searchText
        }

        if (searchProduct.isEmpty()) {
            EmptyState()
        } else {
            ProductListScreen(
                searchText = searchProduct,
                viewModel = hiltViewModel(),
                openDetail = openDetail
            )
        }
    }
}

@Composable
fun MeliChallengeScreen(content: @Composable () -> Unit) {
    MeliChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun HomeAppPreview() {
    MeliChallengeScreen {
        HomeApp {}
    }
}
