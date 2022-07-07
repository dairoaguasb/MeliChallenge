package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("shipping") val shipping: ShippingDTO
)
