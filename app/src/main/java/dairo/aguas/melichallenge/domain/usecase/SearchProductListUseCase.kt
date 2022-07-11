package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow

class SearchProductListUseCase(private val homeRepository: ProductListRepository) {

    operator fun invoke(searchPattern: String): Flow<Result<List<Product>>> =
        homeRepository.searchProductList(searchPattern)
}
