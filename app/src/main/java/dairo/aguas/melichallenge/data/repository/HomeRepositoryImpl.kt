package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.endpoint.HomeAPI
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.HomeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val homeAPI: HomeAPI,
    private val domainExceptionRepository: DomainExceptionRepository
) : HomeRepository {
    override fun searchProductList(searchPattern: String) = flow<Result<List<Product>>> {
        val apiResult = homeAPI.searchProductList(searchPattern)
        emit(Result.Success(apiResult.productList.map { it.toDomainProduct() }))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}
