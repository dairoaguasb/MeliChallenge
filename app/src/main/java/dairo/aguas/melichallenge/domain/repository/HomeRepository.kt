package dairo.aguas.melichallenge.domain.repository

import dairo.aguas.melichallenge.domain.model.Product
import dairo.aguas.melichallenge.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun searchProductList(searchPattern: String): Flow<Result<List<Product>>>
}
