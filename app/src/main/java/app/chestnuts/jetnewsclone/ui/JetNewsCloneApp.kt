package app.chestnuts.jetnewsclone.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.chestnuts.jetnewsclone.data.AppContainer
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme

@Composable
fun JetNewsCloneApp(
    appContainer: AppContainer,
) {
    JetNewsCloneAppTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                //.border(1.dp, Color.Red)
                //.background(Color.Yellow)
        ) {
            Text(text = "compose app")
        }
    }
}