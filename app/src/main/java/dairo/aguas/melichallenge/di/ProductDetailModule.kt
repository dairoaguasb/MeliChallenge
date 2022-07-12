package dairo.aguas.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.data.repository.ProductDetailRepositoryImpl
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository

@Module
@InstallIn(ViewModelComponent::class)
object ProductDetailModule {

    @Provides
    @ViewModelScoped
    fun productDetailRepositoryProvider(
        productAPI: ProductAPI,
        domainExceptionRepository: DomainExceptionRepository
    ): ProductDetailRepository =
        ProductDetailRepositoryImpl(productAPI, domainExceptionRepository)
}
