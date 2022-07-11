package dairo.aguas.melichallenge.ui.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.melichallenge.ui.common.ErrorMessage
import dairo.aguas.melichallenge.ui.common.LoadingIndicator
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.DetailViewData
import dairo.aguas.melichallenge.ui.state.DetailState

@Composable
fun DetailScreen(productId: String) {
    Text(text = productId)
}

@Composable
fun DetailScreenState(detailState: DetailState) {
    when {
        detailState.loading -> {
            LoadingIndicator()
        }
        detailState.error != 0 -> {
            ErrorMessage(message = stringResource(id = detailState.error))
        }
        detailState.products != null -> {
            ProductDetail(detailViewData = detailState.products)
        }
    }
}

@Composable
private fun ProductDetail(detailViewData: DetailViewData) {

}

@Preview
@Composable
private fun ProductDetailPreview() {
    MeliChallengeScreen {
        DetailScreenState(
            detailState = DetailState(loading = true)
        )
    }
}
