package dairo.aguas.melichallenge.ui.product

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.melichallenge.CoroutinesTestRule
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.exception.TimeOutException
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.usecase.SearchProductListUseCase
import dairo.aguas.melichallenge.mock.PRODUCT_MOCK
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.ProductState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private lateinit var productListViewModel: ProductListViewModel
    private val searchProductListUseCase = mockk<SearchProductListUseCase>(relaxed = true)

    @Before
    fun setup() {
        productListViewModel = ProductListViewModel(
            searchProductListUseCase,
            ExceptionHandler(),
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenSearchProductListIsCalledShouldReturnProducts() = mainCoroutineRule.runBlockingTest {
        coEvery {
            searchProductListUseCase.invoke(any())
        } returns flowOf(Result.Success(listOf(PRODUCT_MOCK)))

        val result = arrayListOf<ProductState>()

        val job = launch {
            productListViewModel.state.toList(result)
        }

        productListViewModel.searchProductList("Smart")

        assertEquals(result.size, 2)
        assertEquals(result[0], ProductState())
        assertEquals(result[1].products, listOf(ProductViewData(PRODUCT_MOCK)))
        job.cancel()

        coVerify(exactly = 1) { searchProductListUseCase.invoke(any()) }
        confirmVerified(searchProductListUseCase)
    }

    @Test
    fun whenSearchProductListIsCalledShouldReturnTimeOutException() =
        mainCoroutineRule.runBlockingTest {
            coEvery {
                searchProductListUseCase.invoke(any())
            } returns flowOf(Result.Failure(TimeOutException))

            val result = arrayListOf<ProductState>()

            val job = launch {
                productListViewModel.state.toList(result)
            }

            productListViewModel.searchProductList("Smart")

            assertEquals(result.size, 2)
            assertEquals(result[0], ProductState())
            assertEquals(result[1].error, R.string.error_time_out)
            job.cancel()

            coVerify(exactly = 1) { searchProductListUseCase.invoke(any()) }
            confirmVerified(searchProductListUseCase)
        }

    @Test
    fun whenOnSearchTextChangedIsCalledShouldEmitSearchText() = mainCoroutineRule.runBlockingTest {
        val result = arrayListOf<String>()

        val job = launch {
            productListViewModel.searchText.toList(result)
        }

        productListViewModel.onSearchTextChanged("Smart")

        assertEquals(result.size, 2)
        assertEquals(result[0], "")
        assertEquals(result[1], "Smart")

        job.cancel()
    }
}
