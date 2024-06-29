package app.chestnuts.jetnewsclone.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import app.chestnuts.jetnewsclone.data.AppContainer

@Composable
fun JetNewsCloneApp(
    appContainer: AppContainer,
) {
    Column {
        Text(text = "compose app")
    }
}