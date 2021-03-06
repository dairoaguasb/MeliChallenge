package dairo.aguas.melichallenge.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.common.CardProductHorizontal
import dairo.aguas.melichallenge.ui.common.ErrorMessage
import dairo.aguas.melichallenge.ui.common.LoadingIndicator
import dairo.aguas.melichallenge.ui.common.SliderView
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.DetailViewData
import dairo.aguas.melichallenge.ui.model.PictureViewData
import dairo.aguas.melichallenge.ui.state.DetailState
import dairo.aguas.melichallenge.ui.theme.GreenFreeShipping

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    productId: String
) {
    val state by viewModel.state.collectAsState()
    val refresh by viewModel.refresh.collectAsState()

    if (refresh.not()) {
        viewModel.getProductDetail(productId)
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        DetailScreenState(state)
        ProductsSeller(viewModel)
    }
}

@Composable
fun DetailScreenState(detailState: DetailState) {
    when {
        detailState.loading -> {
            LoadingIndicator()
        }
        detailState.error != 0 -> {
            ErrorMessage(stringResource(id = detailState.error))
        }
        detailState.product != null -> {
            ProductDetail(detailState.product)
        }
    }
}

@Composable
fun ProductDetail(detailViewData: DetailViewData) {
    Column {
        ProductQuantity(detailViewData)
        Text(
            text = detailViewData.title,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        PicturesPreview(detailViewData.pictures)
        ProductPrice(detailViewData)
        Text(
            text = detailViewData.warranty,
            style = MaterialTheme.typography.overline,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))
        ProductDescription(detailViewData)
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun ProductQuantity(detailViewData: DetailViewData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            color = Color.Gray,
            text = if (detailViewData.isNew) {
                stringResource(id = R.string.product_new)
            } else {
                stringResource(id = R.string.product_used)
            },
            style = MaterialTheme.typography.overline
        )
        Text(
            color = Color.Gray,
            text = stringResource(
                id = R.string.product_sold_quantity,
                detailViewData.soldQuantity
            ),
            style = MaterialTheme.typography.overline
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PicturesPreview(pictures: List<PictureViewData>) {
    val pagerState = rememberPagerState()
    Box {
        SliderView(pagerState = pagerState, pictures = pictures)
        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray.copy(alpha = 0.5f))
                .align(Alignment.TopStart)
        ) {
            Text(
                color = Color.Black,
                text = "${pagerState.currentPage + 1}/${pictures.size}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ProductPrice(detailViewData: DetailViewData) {
    if (detailViewData.originalPrice.isNotEmpty()) {
        Text(
            color = Color.DarkGray,
            text = detailViewData.originalPrice,
            textDecoration = TextDecoration.LineThrough,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = detailViewData.price,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(start = 16.dp)
        )
        if (detailViewData.discount.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.product_discount, detailViewData.discount),
                color = GreenFreeShipping,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ProductDescription(detailViewData: DetailViewData) {
    Divider(color = Color.LightGray, thickness = 1.dp)
    Text(
        text = stringResource(id = R.string.product_description),
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(16.dp)
    )
    Text(
        text = detailViewData.description,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.body2,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ProductsSeller(viewModel: DetailViewModel) {
    val productList by viewModel.productList.collectAsState()
    if (productList.isNotEmpty()) {
        Divider(color = Color.LightGray, thickness = 1.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.product_seller_posts),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
            LazyRow {
                items(productList) {
                    CardProductHorizontal(productViewData = it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailPreview() {
    MeliChallengeScreen {
        ProductDetail(
            detailViewData = DetailViewData(
                title = "Celular Apple iPhone 11 (128 Gb) - Negro A15 Bionic Camara 12mpx",
                originalPrice = "$ 4.199.000",
                price = "$ 4.050.000",
                discount = "3%",
                isNew = true,
                soldQuantity = 150,
                warranty = "Garant??a de f??brica: 12 meses",
                description = "Graba videos 4K y captura retratos espectaculares y paisajes incre??bles con el sistema de dos c??maras. Toma grandes fotos con poca luz gracias al modo Noche. Disfruta colores reales en las fotos, videos y juegos con la pantalla Liquid Retina de 6.1 pulgadas (3). Aprovecha un rendimiento sin precedentes en los juegos, la realidad aumentada y la fotograf??a con el chip A13 Bionic. Haz mucho m??s sin necesidad de volver a cargar el tel??fono gracias a su bater??a de larga duraci??n (2). Y no te preocupes si se moja, el iPhone 11 tiene una resistencia al agua de hasta 30 minutos a una profundidad m??xima de 2 metros (1).\\n\\n\\nAvisos Legales\\n(1) El iPhone 11 es resistente a las salpicaduras, al agua y al polvo, y fue probado en condiciones de laboratorio controladas, con una clasificaci??n IP68 seg??n la norma IEC 60529 (hasta 30 minutos a una profundidad m??xima de 2 metros). La resistencia a las salpicaduras, al agua y al polvo no es una condici??n permanente, y podr??a disminuir como consecuencia del uso normal. No intentes cargar un iPhone mojado; consulta el manual del usuario para ver las instrucciones de limpieza y secado. La garant??a no cubre da??os producidos por l??quidos.\\n(2) La duraci??n de la bater??a var??a seg??n el uso y la configuraci??n.\\n(3) La pantalla tiene las esquinas redondeadas. Si se mide en forma de rect??ngulo, la pantalla del iPhone 11 tiene 6.06 pulgadas en diagonal. El ??rea real de visualizaci??n es menor.\\n(4) Los cargadores inal??mbricos Qi se venden por separado.",
                pictures = listOf(
                    PictureViewData("https://http2.mlstatic.com/D_865864-MLA46114990464_052021-O.jpg"),
                    PictureViewData("https://http2.mlstatic.com/D_995869-MLA46114829820_052021-O.jpg"),
                    PictureViewData("https://http2.mlstatic.com/D_861897-MLA46114990467_052021-O.jpg"),
                    PictureViewData("https://http2.mlstatic.com/D_922812-MLA46114829821_052021-O.jpg")

                )
            )
        )
    }
}
