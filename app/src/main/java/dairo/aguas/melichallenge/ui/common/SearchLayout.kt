package dairo.aguas.melichallenge.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dairo.aguas.melichallenge.R
import dairo.aguas.melichallenge.ui.home.MeliChallengeScreen

@Composable
fun SearchLayout(value: String, onValueChange: (String) -> Unit, actionDone: () -> Unit) {
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = value,
            placeholder = { Text(text = stringResource(id = R.string.search_title)) },
            onValueChange = onValueChange,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search_title)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onValueChange(String())
                    focusManager.moveFocus(FocusDirection.Down)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = stringResource(id = R.string.clear_title)
                    )
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    actionDone()
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
private fun SearchLayoutPreview() {
    MeliChallengeScreen {
        SearchLayout(value = "", {}) {}
    }
}
