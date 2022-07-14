package dairo.aguas.melichallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.model.Result
import dairo.aguas.melichallenge.domain.model.fold
import dairo.aguas.melichallenge.domain.usecase.GetProductDetailUseCase
import dairo.aguas.melichallenge.domain.usecase.GetProductListSellerUseCase
import dairo.aguas.melichallenge.ui.model.DetailViewData
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.DetailState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val getProductListSellerUseCase: GetProductListSellerUseCase,
    private val exceptionHandler: ExceptionHandler,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()
    private val _productList = MutableStateFlow(emptyList<ProductViewData>())
    val productList: StateFlow<List<ProductViewData>> = _productList.asStateFlow()
    private val _refresh: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val refresh: StateFlow<Boolean> = _refresh

    fun getProductDetail(id: String) {
        _refresh.value = true
        getProductDetailUseCase.invoke(id).map { result ->
            result.fold(
                onSuccess = { productDetail ->
                    _state.value = DetailState(product = DetailViewData(productDetail))
                    getProductListSeller(productDetail.sellerId)
                },
                onFailure = {
                    _state.value = DetailState(
                        error = exceptionHandler.manageException(it)
                    )
                }
            )
        }.onStart {
            _state.value = DetailState(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    private fun getProductListSeller(sellerId: Int) {
        getProductListSellerUseCase.invoke(sellerId).map { result ->
            if (result is Result.Success) {
                _productList.value = result.data.map { ProductViewData(it) }
            }
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }
}
