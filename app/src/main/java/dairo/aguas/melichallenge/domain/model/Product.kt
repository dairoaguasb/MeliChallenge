package dairo.aguas.melichallenge.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val freeShipping: Boolean
)
