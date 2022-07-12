package dairo.aguas.melichallenge.ui.model

import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.extention.formatMoney

data class DetailViewData(
    val title: String,
    val originalPrice: String,
    val price: String,
    val discount: String,
    val isNew: Boolean,
    val soldQuantity: Int,
    val warranty: String,
    val description: String,
    val pictures: List<PictureViewData>,
    val productSeller: List<ProductViewData>
) {
    constructor(productDetail: ProductDetail) : this(
        title = productDetail.title,
        originalPrice = productDetail.originalPrice.formatMoney(),
        price = productDetail.price.formatMoney(),
        discount = calculateDiscount(productDetail.price, productDetail.originalPrice),
        isNew = productDetail.condition == CONDITION_NEW,
        soldQuantity = productDetail.soldQuantity,
        warranty = productDetail.warranty ?: String(),
        description = productDetail.description,
        pictures = productDetail.pictures.map { PictureViewData(it) },
        productSeller = productDetail.productListSeller.map { ProductViewData(it) }
    )
}

private fun calculateDiscount(price: Double, originalPrice: Double?): String {
    return if (originalPrice != null) {
        "${(100 - (price * 100 / originalPrice)).toInt()} %"
    } else {
        String()
    }
}

private const val CONDITION_NEW = "new"
