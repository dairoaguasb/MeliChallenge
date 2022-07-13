package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ProductDetailRepositoryImpl(
    private val productAPI: ProductAPI,
    private val domainExceptionRepository: DomainExceptionRepository
) : ProductDetailRepository {

    override fun getProductDetail(id: String) = flow<Result<ProductDetail>> {
        val apiResult = productAPI.getProductDetail(id)
        emit(Result.Success(apiResult.toDomainProductDetail()))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }

    override fun getProductDescription(id: String) = flow<Result<String>> {
        val apiResultDescription = productAPI.getProductDescription(id)
        emit(Result.Success(apiResultDescription.description))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }

    override fun getProductListSeller(sellerId: Int) = flow<Result<List<Product>>> {
        val apiResult = productAPI.getProductListSeller(sellerId)
        emit(Result.Success(apiResult.productList.map { it.toDomainProduct() }))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}
