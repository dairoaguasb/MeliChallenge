package dairo.aguas.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.data.repository.ProductDetailRepositoryImpl
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import dairo.aguas.melichallenge.domain.usecase.GetProductDetailUseCase
import dairo.aguas.melichallenge.domain.usecase.GetProductListSellerUseCase
import dairo.aguas.melichallenge.ui.detail.DetailViewModel
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ProductDetailModule {

    @Provides
    fun detailViewModelProvider(
        getProductDetailUseCase: GetProductDetailUseCase,
        getProductListSellerUseCase: GetProductListSellerUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ) = DetailViewModel(
        getProductDetailUseCase,
        getProductListSellerUseCase,
        ExceptionHandler(),
        coroutineDispatcher
    )

    @Provides
    @ViewModelScoped
    fun getProductDetailUseCaseProvider(
        productDetailRepository: ProductDetailRepository
    ) = GetProductDetailUseCase(productDetailRepository)

    @Provides
    @ViewModelScoped
    fun getProductListSellerUseCaseProvider(
        productDetailRepository: ProductDetailRepository
    ) = GetProductListSellerUseCase(productDetailRepository)

    @Provides
    @ViewModelScoped
    fun productDetailRepositoryProvider(
        productAPI: ProductAPI,
        domainExceptionRepository: DomainExceptionRepository
    ): ProductDetailRepository =
        ProductDetailRepositoryImpl(productAPI, domainExceptionRepository)
}
