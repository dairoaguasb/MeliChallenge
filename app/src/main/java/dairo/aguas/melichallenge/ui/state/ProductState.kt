package dairo.aguas.melichallenge.ui.state

import dairo.aguas.melichallenge.ui.model.ProductViewData

data class ProductState(
    val loading: Boolean = false,
    val products: List<ProductViewData> = emptyList(),
    val error: Int = 0
)
