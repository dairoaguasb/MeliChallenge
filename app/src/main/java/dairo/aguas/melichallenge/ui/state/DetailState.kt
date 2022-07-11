package dairo.aguas.melichallenge.ui.state

import dairo.aguas.melichallenge.ui.model.DetailViewData

data class DetailState(
    val loading: Boolean = false,
    val products: DetailViewData? = null,
    val error: Int = 0
)
