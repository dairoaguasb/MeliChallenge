package dairo.aguas.melichallenge.ui.product

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.common.ErrorMessage
import dairo.aguas.melichallenge.ui.common.LoadingIndicator
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.ProductState

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    lazyGridState: LazyGridState
) {
    val productState by viewModel.state.collectAsState()
    ProductScreenStates(
        productState = productState,
        lazyGridState = lazyGridState
    )
}

@Composable
private fun ProductScreenStates(
    productState: ProductState,
    lazyGridState: LazyGridState
) {
    when {
        productState.loading -> {
            LoadingIndicator()
        }
        productState.error != 0 -> {
            ErrorMessage(message = stringResource(id = productState.error))
        }
        else -> {
            ProductList(
                products = productState.products,
                lazyGridState = lazyGridState
            )
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
            ),
            lazyGridState = rememberLazyGridState()
        )
    }
}

@Preview
@Composable
private fun ProductScreenStatesLoadingPreview() {
    MeliChallengeScreen {
        ProductScreenStates(
            productState = ProductState(loading = true),
            lazyGridState = rememberLazyGridState()
        )
    }
}

@Preview
@Composable
private fun ProductScreenStatesErrorPreview() {
    MeliChallengeScreen {
        ProductScreenStates(
            productState = ProductState(error = R.string.error_time_out),
            lazyGridState = rememberLazyGridState()
        )
    }
}
