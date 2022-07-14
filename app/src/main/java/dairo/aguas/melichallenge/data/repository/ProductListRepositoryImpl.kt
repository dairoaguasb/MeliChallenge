package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ProductListRepositoryImpl(
    private val productAPI: ProductAPI,
    private val domainExceptionRepository: DomainExceptionRepository
) : ProductListRepository {

    override fun searchProductList(searchPattern: String) = flow<Result<List<Product>>> {
        val apiResult = productAPI.searchProductList(searchPattern)
        emit(Result.Success(apiResult.productList.map { it.toDomainProduct() }))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}
