package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductDescriptionResponseDTO(
    @SerializedName("plain_text") val description: String
)
