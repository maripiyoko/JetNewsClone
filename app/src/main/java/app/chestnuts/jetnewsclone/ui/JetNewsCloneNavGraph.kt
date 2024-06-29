package app.chestnuts.jetnewsclone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.chestnuts.jetnewsclone.data.AppContainer
import app.chestnuts.jetnewsclone.ui.home.HomeRoute
import app.chestnuts.jetnewsclone.ui.interests.InterestsRoute

@Composable
fun JetNewsCloneNavGraph(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
    startDestination: String = JetNewsCloneDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = JetNewsCloneDestinations.HOME_ROUTE,
        ) { navBackStackEntry ->
            HomeRoute(openDrawer)
        }

        composable(
            route = JetNewsCloneDestinations.INTERESTS_ROUTE,
        ) { navBackStackEntry ->
            InterestsRoute(openDrawer)
        }
    }
}