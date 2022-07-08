package dairo.aguas.melichallenge.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.common.EmptyState
import dairo.aguas.melichallenge.ui.common.ErrorMessage
import dairo.aguas.melichallenge.ui.common.LoadingIndicator
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.ProductState

@Composable
fun ProductListScreen(
    searchText: String,
    viewModel: ProductListViewModel,
    openDetail: (String) -> Unit
) {
    val productState by viewModel.state.collectAsState()

    LaunchedEffect(key1 = searchText) {
        viewModel.searchProductList(searchText)
    }

    ProductScreenStates(
        productState = productState,
        openDetail = openDetail
    )
}

@Composable
private fun ProductScreenStates(
    productState: ProductState,
    openDetail: (String) -> Unit
) {
    when {
        productState.loading -> {
            LoadingIndicator()
        }
        productState.error != 0 -> {
            ErrorMessage(message = stringResource(id = productState.error))
        }
        else -> {
            val productList = productState.products
            if (productList.isNotEmpty()) {
                ProductList(
                    products = productState.products,
                    openDetail = openDetail
                )
            } else {
                EmptyState()
            }
        }
    }
}

@Preview
@Composable
private fun ProductScreenStatesSuccessPreview() {
    MeliChallengeScreen {
        ProductScreenStates(
            productState = ProductState(
                products = listOf(
                    ProductViewData(
                        id = "1",
                        title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                        price = "$ 111.200",
                        thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                        freeShipping = true
                    ),
                    ProductViewData(
                        id = "1",
                        title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                        price = "$ 111.200",
                        thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                        freeShipping = true
                    ),
                    ProductViewData(
                        id = "1",
                        title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                        price = "$ 111.200",
                        thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                        freeShipping = true
                    ),
                    ProductViewData(
                        id = "1",
                        title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                        price = "$ 111.200",
                        thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                        freeShipping = true
                    ),
                    ProductViewData(
                        id = "1",
                        title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                        price = "$ 111.200",
                        thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                        freeShipping = true
                    )
                )
            )
        ){}
    }
}

@Preview
@Composable
private fun ProductScreenStatesLoadingPreview() {
    MeliChallengeScreen {
        ProductScreenStates(
            productState = ProductState(loading = true)
        ){}
    }
}

@Preview
@Composable
private fun ProductScreenStatesErrorPreview() {
    MeliChallengeScreen {
        ProductScreenStates(
            productState = ProductState(error = R.string.error_time_out)
        ){}
    }
}
