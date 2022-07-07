package dairo.aguas.melichallenge.data.endpoint

import dairo.aguas.melichallenge.data.model.ProductResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {

    @GET(SEARCH_PRODUCTS)
    suspend fun searchProductList(@Query("q") searchPattern: String): ProductResponseDTO
}

private const val SEARCH_PRODUCTS = "sites/MLA/search?"
