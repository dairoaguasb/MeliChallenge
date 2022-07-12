package dairo.aguas.melichallenge.data.endpoint

import dairo.aguas.melichallenge.data.model.ProductDescriptionResponseDTO
import dairo.aguas.melichallenge.data.model.ProductDetailResponseDTO
import dairo.aguas.melichallenge.data.model.ProductResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductAPI {

    @GET(SEARCH_PRODUCTS)
    suspend fun searchProductList(@Query("q") searchPattern: String): ProductResponseDTO

    @GET(PRODUCT_DETAIL)
    suspend fun getProductDetail(@Path("id") id: String): ProductDetailResponseDTO

    @GET(PRODUCT_DESCRIPTION)
    suspend fun getProductDescription(@Path("id") id: String): ProductDescriptionResponseDTO
}

private const val SEARCH_PRODUCTS = "sites/MLA/search?"
private const val PRODUCT_DETAIL = "items/{id}"
private const val PRODUCT_DESCRIPTION = "items/{id}/description"
