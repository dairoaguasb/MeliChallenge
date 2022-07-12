package dairo.aguas.melichallenge.data.model

import dairo.aguas.melichallenge.mock.PRODUCT_DTO_MOCK
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDTOTest {

    @Test
    fun givenProductDTOShouldMapToDomain() {
        val productDomain = PRODUCT_DTO_MOCK.toDomainProduct()

        assertEquals("id", productDomain.id)
        assertEquals("title", productDomain.title)
        assertEquals(10.0, productDomain.price, 0.0)
        assertEquals("thumbnail", productDomain.thumbnail)
        assertEquals(true, productDomain.freeShipping)
    }
}
