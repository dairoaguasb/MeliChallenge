package dairo.aguas.melichallenge.ui.model

import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.extention.formatMoney

data class ProductViewData(
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
    val freeShipping: Boolean
) {
    constructor(product: Product) : this(
        id = product.id,
        title = product.title,
        price = product.price.formatMoney(),
        thumbnail = product.thumbnail.replace(OLD_VALUE, NEW_VALUE),
        freeShipping = product.freeShipping
    )
}

private const val OLD_VALUE = "http:"
private const val NEW_VALUE = "https:"
