package dairo.aguas.melichallenge.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.model.fold
import dairo.aguas.melichallenge.domain.usecase.SearchProductListUseCase
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.state.ProductState
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
class ProductListViewModel @Inject constructor(
    private val searchProductListUseCase: SearchProductListUseCase,
    private val exceptionHandler: ExceptionHandler,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state.asStateFlow()
    private val _searchText: MutableStateFlow<String> = MutableStateFlow(String())
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    fun searchProductList(searchPattern: String) {
        searchProductListUseCase.invoke(searchPattern).map { result ->
            result.fold(
                onSuccess = { productList ->
                    _state.value = ProductState(
                        products = productList.map { ProductViewData(it) }
                    )
                },
                onFailure = {
                    _state.value = ProductState(
                        error = exceptionHandler.manageException(it)
                    )
                }
            )
        }.onStart {
            _state.value = ProductState(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    fun onSearchTextChanged(changedSearchText: String) {
        _searchText.value = changedSearchText
    }
}
