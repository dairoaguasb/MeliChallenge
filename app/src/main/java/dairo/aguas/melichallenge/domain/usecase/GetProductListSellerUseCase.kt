package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow

class GetProductListSellerUseCase(private val productDetailRepository: ProductDetailRepository) {

    operator fun invoke(sellerId: Int): Flow<Result<List<Product>>> =
        productDetailRepository.getProductListSeller(sellerId)
}
