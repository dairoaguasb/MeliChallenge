package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow

class GetProductDetailUseCase(private val productDetailRepository: ProductDetailRepository) {

    operator fun invoke(id: String): Flow<Result<ProductDetail>> =
        productDetailRepository.getProductDetail(id)
}
