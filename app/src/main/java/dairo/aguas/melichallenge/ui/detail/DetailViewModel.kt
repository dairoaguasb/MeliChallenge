package dairo.aguas.melichallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dairo.aguas.melichallenge.domain.exception.ExceptionHandler
import dairo.aguas.melichallenge.domain.model.fold
import dairo.aguas.melichallenge.domain.usecase.GetProductDetailUseCase
import dairo.aguas.melichallenge.ui.model.DetailViewData
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
    private val exceptionHandler: ExceptionHandler,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    fun getProductDetail(id: String) {
        getProductDetailUseCase.invoke(id).map { result ->
            result.fold(
                onSuccess = { productDetail ->
                    _state.value = DetailState(product = DetailViewData(productDetail))
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
}
