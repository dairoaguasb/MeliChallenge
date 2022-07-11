package dairo.aguas.melichallenge.ui.model

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
)

data class PictureViewData(
    val url: String
)
