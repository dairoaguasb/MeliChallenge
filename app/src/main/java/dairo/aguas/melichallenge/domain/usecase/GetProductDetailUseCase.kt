package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.fold
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.flow

class GetProductDetailUseCase(private val productDetailRepository: ProductDetailRepository) {

    operator fun invoke(id: String) = flow<Result<ProductDetail>> {
        productDetailRepository.getProductDetail(id).collect { resultProductDetail ->
            resultProductDetail.fold(
                onSuccess = { productDetail ->
                    productDetailRepository.getProductListSeller(productDetail.sellerId)
                        .collect { resultProductList ->
                            resultProductList.fold(
                                onSuccess = { productList ->
                                    emit(
                                        Result.Success(
                                            productDetail.apply {
                                                this.productListSeller = productList
                                            }
                                        )
                                    )
                                },
                                onFailure = {
                                    emit(Result.Failure(it))
                                }
                            )
                        }
                },
                onFailure = {
                    emit(Result.Failure(it))
                }
            )
        }
    }
}
