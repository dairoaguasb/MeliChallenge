package dairo.aguas.melichallenge.domain.model

data class ProductDetail(
    val id: String,
    val title: String,
    val sellerId: Int,
    val price: Double,
    val originalPrice: Double?,
    val condition: String,
    val soldQuantity: Int,
    val warranty: String?,
    val description: String,
    val pictures: List<Pictures>
)
