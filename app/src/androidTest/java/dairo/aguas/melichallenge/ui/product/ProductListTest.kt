package dairo.aguas.melichallenge.ui.product

import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import dairo.aguas.melichallenge.ui.model.ProductViewData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val productList: List<ProductViewData> = (1..60).map {
        ProductViewData(
            id = it.toString(),
            title = "title $it",
            price = "price $it",
            thumbnail = "",
            freeShipping = true
        )
    }

    @Before
    fun setup() {
        composeTestRule.setContent {
            ProductList(products = productList, openDetail = {})
        }
    }

    @Test
    fun whenProductListIsVisibleShouldNavigateToItem30(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(30)

        onNodeWithText("title 30").assertExists()
        onNodeWithText("price 30").assertExists()
    }
}
