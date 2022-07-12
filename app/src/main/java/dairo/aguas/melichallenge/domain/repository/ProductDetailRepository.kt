package dairo.aguas.melichallenge.domain.repository

import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {

    fun getProductDetail(id: String): Flow<Result<ProductDetail>>
}
