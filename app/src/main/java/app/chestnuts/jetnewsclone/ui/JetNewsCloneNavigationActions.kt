package app.chestnuts.jetnewsclone.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object JetNewsCloneDestinations {
    const val HOME_ROUTE = "home"
    const val INTERESTS_ROUTE = "interests"
    const val POST_DETAIL_ROUTE = "posts"
}

internal const val postIdArg = "postId"

class JetNewsCloneNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(JetNewsCloneDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToInterests: () -> Unit = {
        navController.navigate(JetNewsCloneDestinations.INTERESTS_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToPostDetail: (String) -> Unit = { postId ->
        navController.navigate(
            route = "${JetNewsCloneDestinations.POST_DETAIL_ROUTE}/$postId",
        ) {
            launchSingleTop = true
        }
    }
}
