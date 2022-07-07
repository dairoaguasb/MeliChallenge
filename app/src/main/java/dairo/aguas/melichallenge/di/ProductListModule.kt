package dairo.aguas.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.data.repository.ExceptionRepositoryImpl
import dairo.aguas.melichallenge.data.repository.ProductListRepositoryImpl
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import dairo.aguas.melichallenge.domain.usecase.SearchProductListUseCase
import dairo.aguas.melichallenge.ui.product.ProductListViewModel
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ProductListModule {

    @Provides
    fun productListViewModelProvider(
        searchProductListUseCase: SearchProductListUseCase,
        coroutineDispatcher: CoroutineDispatcher
    ) = ProductListViewModel(
        searchProductListUseCase,
        ExceptionHandler(),
        coroutineDispatcher
    )

    @Provides
    @ViewModelScoped
    fun searchProductListUseCaseProvider(
        productListRepository: ProductListRepository
    ) = SearchProductListUseCase(productListRepository)

    @Provides
    @ViewModelScoped
    fun homeRepositoryProvider(
        productAPI: ProductAPI,
        domainExceptionRepository: DomainExceptionRepository
    ): ProductListRepository =
        ProductListRepositoryImpl(productAPI, domainExceptionRepository)

    @Provides
    @ViewModelScoped
    fun exceptionRepositoryProvider(): DomainExceptionRepository =
        ExceptionRepositoryImpl()
}
