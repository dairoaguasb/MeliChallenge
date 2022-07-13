package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.exception.NoConnectivityDomainException
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.getFailure
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.model.isFailure
import dairo.aguas.melichallenge.domain.model.isSuccess
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchProductListUseCaseTest {

    private lateinit var searchProductListUseCase: SearchProductListUseCase
    private val productListRepository = mockk<ProductListRepository>(relaxed = true)

    @Before
    fun setup() {
        searchProductListUseCase = SearchProductListUseCase(productListRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnProductList() = runBlocking {
        val product: Product = mockk()
        coEvery {
            productListRepository.searchProductList(any())
        } returns flowOf(Result.Success(listOf(product)))

        searchProductListUseCase.invoke("tv").collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), listOf(product))
        }

        coVerify(exactly = 1) { productListRepository.searchProductList(any()) }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnNoConnectivityDomainException() = runBlocking {
        coEvery {
            productListRepository.searchProductList(any())
        } returns flowOf(Result.Failure(NoConnectivityDomainException))

        searchProductListUseCase.invoke("tv").collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }

        coVerify(exactly = 1) { productListRepository.searchProductList(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(productListRepository)
    }
}
