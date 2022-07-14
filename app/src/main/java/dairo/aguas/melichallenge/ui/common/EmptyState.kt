package dairo.aguas.melichallenge.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = stringResource(id = R.string.search_title),
            modifier = Modifier.size(100.dp, 100.dp),
            tint = Color.LightGray
        )
        Text(
            text = stringResource(id = R.string.not_find_products),
            color = Color.DarkGray,
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 18.sp
            )
        )
        Text(
            text = stringResource(id = R.string.word_spelled_correctly),
            color = Color.Gray,
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 18.sp
            )
        )
    }
}

@Preview
@Composable
fun EmptyStatePreview() {
    MeliChallengeScreen {
        EmptyState()
    }
}
