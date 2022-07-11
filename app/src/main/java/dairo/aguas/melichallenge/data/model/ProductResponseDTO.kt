package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponseDTO(
    @SerializedName("results") val productList: List<ProductDTO>
)
