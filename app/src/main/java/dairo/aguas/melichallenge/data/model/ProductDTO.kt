package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.melichallenge.domain.model.Product

data class ProductDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("shipping") val shipping: ShippingDTO
) {
    fun toDomainProduct(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            thumbnail = thumbnail,
            freeShipping = shipping.freeShipping
        )
    }
}
