package dairo.aguas.melichallenge.ui.product

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.common.ErrorMessage
import dairo.aguas.melichallenge.ui.common.LoadingIndicator
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.ProductState

@Composable
fun ProductScreen() {
}

@Composable
private fun ProductsState(
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
private fun ProductsStateSuccessPreview() {
    MeliChallengeScreen {
        ProductsState(
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
private fun ProductsStateLoadingPreview() {
    MeliChallengeScreen {
        ProductsState(
            productState = ProductState(loading = true),
            lazyGridState = rememberLazyGridState()
        )
    }
}

@Preview
@Composable
private fun ProductsStateErrorPreview() {
    MeliChallengeScreen {
        ProductsState(
            productState = ProductState(error = R.string.error_time_out),
            lazyGridState = rememberLazyGridState()
        )
    }
}
