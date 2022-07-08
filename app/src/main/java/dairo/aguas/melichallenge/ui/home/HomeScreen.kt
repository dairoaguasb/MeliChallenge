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
import dairo.aguas.melichallenge.ui.common.EmptyState
import dairo.aguas.melichallenge.ui.common.SearchLayout
import dairo.aguas.melichallenge.ui.product.ProductListScreen
import dairo.aguas.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun HomeApp() {
    MeliChallengeScreen {
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
                    viewModel = hiltViewModel()
                ) {
                }
            }
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
    HomeApp()
}
