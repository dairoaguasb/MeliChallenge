package dairo.aguas.melichallenge.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.melichallenge.domain.model.Pictures

data class PicturesDTO(
    @SerializedName("secure_url") val url: String
) {
    fun toDomainPictures(): Pictures {
        return Pictures(
            url = url
        )
    }
}
