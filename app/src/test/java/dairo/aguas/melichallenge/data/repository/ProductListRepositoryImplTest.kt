package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.endpoint.ProductAPI
import dairo.aguas.melichallenge.data.model.ProductResponseDTO
import dairo.aguas.melichallenge.domain.exception.NoConnectivityDomainException
import dairo.aguas.melichallenge.domain.model.getFailure
import dairo.aguas.melichallenge.domain.model.getSuccess
import dairo.aguas.melichallenge.domain.model.isFailure
import dairo.aguas.melichallenge.domain.model.isSuccess
import dairo.aguas.melichallenge.domain.repository.ProductListRepository
import dairo.aguas.melichallenge.mock.PRODUCT_DTO_MOCK
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ProductListRepositoryImplTest {

    private lateinit var productListRepository: ProductListRepository
    private val productAPI = mockk<ProductAPI>(relaxed = true)

    @Before
    fun setup() {
        productListRepository = ProductListRepositoryImpl(
            productAPI,
            ExceptionRepositoryImpl()
        )
    }

    @Test
    fun whenSearchProductListIsCalledShouldReturnProductList() = runBlocking {
        val productResponseDTO = ProductResponseDTO(listOf(PRODUCT_DTO_MOCK))
        coEvery { productAPI.searchProductList(any()) } returns productResponseDTO

        productListRepository.searchProductList("tv").collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), listOf(PRODUCT_DTO_MOCK.toDomainProduct()))
        }

        coVerify(exactly = 1) { productAPI.searchProductList(any()) }
    }

    @Test
    fun whenSearchProductListIsCalledShouldReturnFailure() = runBlocking {
        coEvery { productAPI.searchProductList(any()) } answers { throw UnknownHostException() }

        productListRepository.searchProductList("tv").collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }

        coVerify(exactly = 1) { productAPI.searchProductList(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(productAPI)
    }
}
