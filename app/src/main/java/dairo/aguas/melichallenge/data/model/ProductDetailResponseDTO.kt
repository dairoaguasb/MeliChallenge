package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.melichallenge.domain.model.ProductDetail

data class ProductDetailResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("seller_id") val sellerId: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("condition") val condition: String,
    @SerializedName("sold_quantity") val soldQuantity: Int,
    @SerializedName("warranty") val warranty: String?,
    @SerializedName("pictures") val pictures: List<PicturesDTO>
) {
    fun toDomainProductDetail(): ProductDetail {
        return ProductDetail(
            id = id,
            title = title,
            sellerId = sellerId,
            price = price,
            originalPrice = originalPrice,
            condition = condition,
            soldQuantity = soldQuantity,
            warranty = warranty,
            pictures = pictures.map { it.toDomainPictures() }
        )
    }
}
