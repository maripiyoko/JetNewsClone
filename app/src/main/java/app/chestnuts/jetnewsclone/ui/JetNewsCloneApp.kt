package app.chestnuts.jetnewsclone.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.chestnuts.jetnewsclone.data.AppContainer
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme

@Composable
fun JetNewsCloneApp(
    appContainer: AppContainer,
) {
    JetNewsCloneAppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            JetNewsCloneNavigationActions(navController)
        }
        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: JetNewsCloneDestinations.HOME_ROUTE

        JetNewsCloneNavGraph(
            appContainer = appContainer,
            navController = navController,
        )
    }
}