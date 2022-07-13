package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.fold
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetProductDetailUseCase(private val productDetailRepository: ProductDetailRepository) {

    operator fun invoke(id: String) = flow<Result<ProductDetail>> {
        productDetailRepository.getProductDetail(id).collect { resultProductDetail ->
            resultProductDetail.fold(
                onSuccess = { productDetail ->
                    productDetail.description =
                        productDetailRepository.getProductDescription(id).first().getSuccess()
                    emit(Result.Success(productDetail))
                },
                onFailure = {
                    emit(Result.Failure(it))
                }
            )
        }
    }
}
