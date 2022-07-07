package dairo.aguas.melichallenge.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.ProductViewData
import dairo.aguas.melichallenge.ui.theme.GreenFreeShipping

@Composable
fun CardProduct(
    productViewData: ProductViewData,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(4.dp)) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(productViewData.thumbnail),
                contentDescription = productViewData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
            Text(
                color = Color.Black,
                text = productViewData.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            Text(
                color = Color.Black,
                text = productViewData.price,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            if (productViewData.freeShipping) {
                Text(
                    color = GreenFreeShipping,
                    text = stringResource(R.string.free_shipping),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun CardProductPreview() {
    MeliChallengeScreen {
        CardProduct(
            productViewData = ProductViewData(
                id = "1",
                title = "Smart TV Samsung Series 7 UN50AU7000GCZB LED 4K 50\" 220V - 240V",
                price = "$ 111.200",
                thumbnail = "https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp",
                freeShipping = true
            )
        )
    }
}
