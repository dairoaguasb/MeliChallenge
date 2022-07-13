package dairo.aguas.melichallenge.ui.model

import dairo.aguas.melichallenge.mock.PRODUCT_MOCK
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductViewDataTest {

    @Test
    fun givenDomainProductShouldToMapProductViewData() {
        val productViewData = ProductViewData(PRODUCT_MOCK)

        assertEquals("id", productViewData.id)
        assertEquals("title", productViewData.title)
        assertEquals("$ 10", productViewData.price)
        assertEquals("https://thumbnail", productViewData.thumbnail)
        assertEquals(true, productViewData.freeShipping)
    }
}
