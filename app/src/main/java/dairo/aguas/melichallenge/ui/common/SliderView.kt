package dairo.aguas.melichallenge.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen
import dairo.aguas.melichallenge.ui.model.PictureViewData

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(pagerState: PagerState, pictures: List<PictureViewData>) {
    val imageUrl = remember { mutableStateOf("") }
    HorizontalPager(
        state = pagerState,
        count = pictures.size,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) { page ->
        imageUrl.value = pictures[page].url

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl.value),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize()
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
private fun SliderViewPreview() {
    MeliChallengeScreen {
        SliderView(
            pagerState = rememberPagerState(),
            pictures = listOf(
                PictureViewData("https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp"),
                PictureViewData("https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp"),
                PictureViewData("https://http2.mlstatic.com/D_NQ_NP_787221-MLA48007684849_102021-V.webp")
            )
        )
    }
}
