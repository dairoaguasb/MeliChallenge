package dairo.aguas.melichallenge.domain.usecase

import dairo.aguas.melichallenge.domain.exception.BadRequestException
import dairo.aguas.melichallenge.domain.exception.NoConnectivityDomainException
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.getFailure
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.model.isFailure
import dairo.aguas.melichallenge.domain.model.isSuccess
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import dairo.aguas.melichallenge.mock.PRODUCT_DETAIL_MOCK
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

class GetProductDetailUseCaseTest {

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase
    private val productDetailRepository = mockk<ProductDetailRepository>(relaxed = true)

    @Before
    fun setup() {
        getProductDetailUseCase = GetProductDetailUseCase(productDetailRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnProductDetail() = runBlocking {
        val productDetail = PRODUCT_DETAIL_MOCK

        coEvery {
            productDetailRepository.getProductDetail(any())
        } returns flowOf(Result.Success(productDetail))

        coEvery {
            productDetailRepository.getProductDescription(any())
        } returns flowOf(Result.Success("description new"))

        getProductDetailUseCase.invoke("MELI").collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), productDetail)
            assertEquals(result.getSuccess()?.description, "description new")
        }

        coVerify(exactly = 1) { productDetailRepository.getProductDetail(any()) }
        coVerify(exactly = 1) { productDetailRepository.getProductDescription(any()) }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnNoConnectivityDomainException() = runBlocking {
        coEvery {
            productDetailRepository.getProductDetail(any())
        } returns flowOf(Result.Failure(NoConnectivityDomainException))

        getProductDetailUseCase.invoke("MELI").collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) { productDetailRepository.getProductDetail(any()) }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnProductDetailAndDescriptionIsNull() = runBlocking {
        val productDetail = PRODUCT_DETAIL_MOCK

        coEvery {
            productDetailRepository.getProductDetail(any())
        } returns flowOf(Result.Success(productDetail))

        coEvery {
            productDetailRepository.getProductDescription(any())
        } returns flowOf(Result.Failure(BadRequestException))

        getProductDetailUseCase.invoke("MELI").collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), productDetail)
            assertEquals(result.getSuccess()?.description, null)
        }

        coVerify(exactly = 1) { productDetailRepository.getProductDetail(any()) }
        coVerify(exactly = 1) { productDetailRepository.getProductDescription(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(productDetailRepository)
    }
}
