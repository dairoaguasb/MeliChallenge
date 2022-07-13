package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.exception.NoConnectivityDomainException
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.getFailure
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.model.isFailure
import dairo.aguas.melichallenge.domain.model.isSuccess
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetProductListSellerUseCaseTest {

    private lateinit var getProductListSellerUseCase: GetProductListSellerUseCase
    private val productDetailRepository = mockk<ProductDetailRepository>(relaxed = true)

    @Before
    fun setup() {
        getProductListSellerUseCase = GetProductListSellerUseCase(productDetailRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnProductList() = runBlocking {
        val product: Product = mockk()
        coEvery {
            productDetailRepository.getProductListSeller(any())
        } returns flowOf(Result.Success(listOf(product)))

        getProductListSellerUseCase.invoke(12345).collect { result ->
            assert(result.isSuccess())
            Assert.assertEquals(result.getSuccess(), listOf(product))
        }

        coVerify(exactly = 1) { productDetailRepository.getProductListSeller(any()) }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnNoConnectivityDomainException() = runBlocking {
        coEvery {
            productDetailRepository.getProductListSeller(any())
        } returns flowOf(Result.Failure(NoConnectivityDomainException))

        getProductListSellerUseCase.invoke(12345).collect { result ->
            assert(result.isFailure())
            Assert.assertEquals(result.getFailure(), NoConnectivityDomainException)
        }

        coVerify(exactly = 1) { productDetailRepository.getProductListSeller(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(productDetailRepository)
    }
}
