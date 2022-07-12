package dairo.aguas.melichallenge.ui.model

import dairo.aguas.melichallenge.domain.model.Pictures

data class PictureViewData(
    val url: String
) {
    constructor(pictures: Pictures) : this(
        url = pictures.url
    )
}
