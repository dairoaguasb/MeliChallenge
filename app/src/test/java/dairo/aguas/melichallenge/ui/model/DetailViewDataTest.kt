package dairo.aguas.melichallenge.ui.model

import dairo.aguas.melichallenge.mock.PRODUCT_DETAIL_MOCK
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailViewDataTest {

    @Test
    fun givenDomainProductDetailShouldToMapDetailViewData() {
        val detailViewData = DetailViewData(PRODUCT_DETAIL_MOCK)

        assertEquals("title", detailViewData.title)
        assertEquals("$ 20", detailViewData.originalPrice)
        assertEquals("$ 10", detailViewData.price)
        assertEquals("50 %", detailViewData.discount)
        assertEquals(true, detailViewData.isNew)
        assertEquals(10, detailViewData.soldQuantity)
        assertEquals("warranty", detailViewData.warranty)
        assertEquals(emptyList<PictureViewData>(), detailViewData.pictures)
    }
}
