package dairo.aguas.melichallenge.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dairo.aguas.melichallenge.ui.navigation.Navigation
import dairo.aguas.melichallenge.ui.theme.MeliChallengeTheme

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    MeliChallengeScreen {
        Navigation(navController = navController)
    }
}

@Composable
fun MeliChallengeScreen(content: @Composable () -> Unit) {
    MeliChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
