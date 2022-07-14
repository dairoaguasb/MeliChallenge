package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow

class SearchProductListUseCase(private val productListRepository: ProductListRepository) {

    operator fun invoke(searchPattern: String): Flow<Result<List<Product>>> =
        productListRepository.searchProductList(searchPattern)
}
