package dairo.aguas.melichallenge.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.melichallenge.CoroutinesTestRule
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.exception.TimeOutException
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.usecase.GetProductDetailUseCase
import dairo.aguas.melichallenge.domain.usecase.GetProductListSellerUseCase
import dairo.aguas.melichallenge.mock.PRODUCT_DETAIL_MOCK
import dairo.aguas.melichallenge.mock.PRODUCT_MOCK
import dairo.aguas.melichallenge.ui.model.DetailViewData
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.DetailState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private lateinit var detailViewModel: DetailViewModel
    private val getProductDetailUseCase = mockk<GetProductDetailUseCase>(relaxed = true)
    private val getProductListSellerUseCase = mockk<GetProductListSellerUseCase>(relaxed = true)

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(
            getProductDetailUseCase,
            getProductListSellerUseCase,
            ExceptionHandler(),
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenGetProductDetailIsCalledShouldReturnProducts() = mainCoroutineRule.runBlockingTest {
        coEvery {
            getProductDetailUseCase.invoke(any())
        } returns flowOf(Result.Success(PRODUCT_DETAIL_MOCK))

        val result = arrayListOf<DetailState>()

        val job = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getProductDetail("MEL123")

        assertEquals(result.size, 2)
        assertEquals(result[0], DetailState())
        assertEquals(result[1].product, DetailViewData(PRODUCT_DETAIL_MOCK))

        job.cancel()

        coVerify(exactly = 1) { getProductDetailUseCase.invoke(any()) }
        coVerify(exactly = 1) { getProductListSellerUseCase.invoke(any()) }
    }

    @Test
    fun whenGetProductDetailIsCalledShouldReturnTimeOutException() =
        mainCoroutineRule.runBlockingTest {
            coEvery {
                getProductDetailUseCase.invoke(any())
            } returns flowOf(Result.Failure(TimeOutException))

            val result = arrayListOf<DetailState>()

            val job = launch {
                detailViewModel.state.toList(result)
            }

            detailViewModel.getProductDetail("MEL123")

            assertEquals(result.size, 2)
            assertEquals(result[0], DetailState())
            assertEquals(result[1].error, R.string.error_time_out)

            job.cancel()

            coVerify(exactly = 1) { getProductDetailUseCase.invoke(any()) }
        }

    @Test
    fun whenGetProductListSellerIsCalledShouldReturn() = mainCoroutineRule.runBlockingTest {
        coEvery {
            getProductListSellerUseCase.invoke(any())
        } returns flowOf(Result.Success(listOf(PRODUCT_MOCK)))

        val method = detailViewModel.javaClass.getDeclaredMethod(
            "getProductListSeller",
            Int::class.java
        ).apply { isAccessible = true }

        val result = arrayListOf<List<ProductViewData>>()

        val job = launch {
            detailViewModel.productList.toList(result)
        }

        method.invoke(detailViewModel, 12345)

        assertEquals(result.size, 2)
        assertEquals(result[0], emptyList<ProductViewData>())
        assertEquals(result[1], listOf(ProductViewData(PRODUCT_MOCK)))

        job.cancel()

        coVerify(exactly = 1) { getProductListSellerUseCase.invoke(any()) }
    }

    @After
    fun tearDown() {
        confirmVerified(getProductDetailUseCase, getProductListSellerUseCase)
    }
}
