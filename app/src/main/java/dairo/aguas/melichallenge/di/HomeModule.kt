package dairo.aguas.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dairo.aguas.melichallenge.data.endpoint.HomeAPI
import dairo.aguas.melichallenge.data.repository.ExceptionRepositoryImpl
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun exceptionRepositoryProvider(): DomainExceptionRepository =
        ExceptionRepositoryImpl()

    @Provides
    @ViewModelScoped
    fun homeAPIProvider(retrofit: Retrofit): HomeAPI =
        retrofit.create(HomeAPI::class.java)
}
