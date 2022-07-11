package dairo.aguas.melichallenge.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dairo.aguas.melichallenge.ui.common.CardProduct
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.ProductViewData

@Composable
fun ProductList(
    products: List<ProductViewData>,
    openDetail: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (products.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(products) {
                    CardProduct(
                        productViewData = it,
                        modifier = Modifier.clickable { openDetail(it.id) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductListPreview() {
    MeliChallengeScreen {
        ProductList(
            products = listOf(
                ProductViewData(
                    id = "1",
                    title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                    price = "$ 111.200",
                    thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                    freeShipping = false
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
        ) {}
    }
}
