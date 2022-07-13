package dairo.aguas.melichallenge.mock

import dairo.aguas.melichallenge.data.model.ProductDTO
import dairo.aguas.melichallenge.data.model.ShippingDTO
import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.ProductDetail

val SHIPPING_MOCK = ShippingDTO(freeShipping = true)

val PRODUCT_DTO_MOCK = ProductDTO(
    id = "id",
    title = "title",
    price = 10.0,
    thumbnail = "thumbnail",
    shipping = SHIPPING_MOCK
)

val PRODUCT_DETAIL_MOCK = ProductDetail(
    id = "id",
    title = "title",
    sellerId = 12345,
    price = 10.0,
    originalPrice = 20.0,
    condition = "new",
    soldQuantity = 10,
    warranty = "warranty",
    description = "description",
    pictures = emptyList()
)

val PRODUCT_MOCK = Product(
    id = "id",
    title = "title",
    price = 10.0,
    thumbnail = "thumbnail",
    freeShipping = true
)
