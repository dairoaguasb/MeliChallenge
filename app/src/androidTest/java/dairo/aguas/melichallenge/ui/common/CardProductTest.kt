package dairo.aguas.melichallenge.ui.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.mock.PRODUCT_VIEW_DATA_MOCK
import org.junit.Rule
import org.junit.Test

internal class CardProductTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun whenCardProductIsVisibleShouldValidateInformation(): Unit = with(composeTestRule) {
        setContent {
            CardProduct(productViewData = PRODUCT_VIEW_DATA_MOCK)
        }
        onNodeWithText(PRODUCT_VIEW_DATA_MOCK.title).assertExists()
        onNodeWithText(PRODUCT_VIEW_DATA_MOCK.price).assertExists()
        onNodeWithText(ctx.getString(R.string.free_shipping)).assertExists()
    }

    @Test
    fun whenCardProductIsVisibleShouldNotShowFreeShipping(): Unit = with(composeTestRule) {
        setContent {
            CardProduct(productViewData = PRODUCT_VIEW_DATA_MOCK.copy(freeShipping = false))
        }

        onNodeWithText(PRODUCT_VIEW_DATA_MOCK.title).assertExists()
        onNodeWithText(PRODUCT_VIEW_DATA_MOCK.price).assertExists()
        onNodeWithText(ctx.getString(R.string.free_shipping)).assertDoesNotExist()
    }
}
