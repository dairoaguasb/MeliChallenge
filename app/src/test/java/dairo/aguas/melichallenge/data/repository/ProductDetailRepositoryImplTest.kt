package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.data.model.ProductDescriptionResponseDTO
import dairo.aguas.melichallenge.data.model.ProductDetailResponseDTO
import dairo.aguas.melichallenge.data.model.ProductResponseDTO
import dairo.aguas.melichallenge.domain.exception.NoConnectivityDomainException
import dairo.aguas.melichallenge.domain.model.ProductDetail
import dairo.aguas.melichallenge.domain.model.getFailure
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.model.isFailure
import dairo.aguas.melichallenge.domain.model.isSuccess
import dairo.aguas.melichallenge.domain.repository.ProductDetailRepository
import dairo.aguas.melichallenge.mock.PRODUCT_DTO_MOCK
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ProductDetailRepositoryImplTest {

    private lateinit var productDetailRepository: ProductDetailRepository
    private val productAPI = mockk<ProductAPI>(relaxed = true)

    @Before
    fun setup() {
        productDetailRepository = ProductDetailRepositoryImpl(
            productAPI,
            ExceptionRepositoryImpl()
        )
    }

    @Test
    fun whenGetProductDetailIsCalledShouldReturnProductDetail() = runBlocking {
        val productDetailResponseDTO: ProductDetailResponseDTO = mockk()
        val productDescriptionResponseDTO = ProductDescriptionResponseDTO(description = "desc")
        val productDetailDomain: ProductDetail = mockk()

        coEvery { productAPI.getProductDetail(any()) } returns productDetailResponseDTO
        coEvery { productAPI.getProductDescription(any()) } returns productDescriptionResponseDTO
        every { productDetailResponseDTO.toDomainProductDetail(any()) } returns productDetailDomain

        productDetailRepository.getProductDetail("MLI111").collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), productDetailDomain)
        }
        coVerify(exactly = 1) { productAPI.getProductDetail(any()) }
        coVerify(exactly = 1) { productAPI.getProductDescription(any()) }
        coVerify(exactly = 1) { productDetailResponseDTO.toDomainProductDetail(any()) }
        confirmVerified(productDetailResponseDTO)
    }

    @Test
    fun whenGetProductDetailIsCalledShouldReturnFailure() = runBlocking {
        coEvery { productAPI.getProductDetail(any()) } answers { throw UnknownHostException() }

        productDetailRepository.getProductDetail("MLI111").collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) { productAPI.getProductDetail(any()) }
    }

    @Test
    fun whenGetProductListSellerIsCalledShouldReturnProduct() = runBlocking {
        val productResponseDTO = ProductResponseDTO(listOf(PRODUCT_DTO_MOCK))

        coEvery { productAPI.getProductListSeller(any()) } returns productResponseDTO

        productDetailRepository.getProductListSeller(123).collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), listOf(PRODUCT_DTO_MOCK.toDomainProduct()))
        }
        coVerify(exactly = 1) { productAPI.getProductListSeller(any()) }
    }

    @Test
    fun whenGetProductListSellerIsCalledShouldReturnFailure() = runBlocking {
        coEvery { productAPI.getProductListSeller(any()) } answers { throw UnknownHostException() }

        productDetailRepository.getProductListSeller(123).collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) { productAPI.getProductListSeller(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(productAPI)
    }
}
