package dairo.aguas.melichallenge.mock

import dairo.aguas.melichallenge.data.model.ProductDTO
import dairo.aguas.melichallenge.data.model.ShippingDTO

val SHIPPING_MOCK = ShippingDTO(freeShipping = true)

val PRODUCT_DTO_MOCK = ProductDTO(
    id = "id",
    title = "title",
    price = 10.0,
    thumbnail = "thumbnail",
    shipping = SHIPPING_MOCK
)
