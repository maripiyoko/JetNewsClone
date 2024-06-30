package app.chestnuts.jetnewsclone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.chestnuts.jetnewsclone.ui.home.HomeRoute
import app.chestnuts.jetnewsclone.ui.interests.InterestsRoute
import app.chestnuts.jetnewsclone.ui.posts.PostsDetailRoute

@Composable
fun JetNewsCloneNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigateToPostDetail: (String) -> Unit,
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
        ) { _ ->
            HomeRoute(openDrawer, onSelectPost = navigateToPostDetail)
        }

        composable(
            route = JetNewsCloneDestinations.INTERESTS_ROUTE,
        ) { _ ->
            InterestsRoute(openDrawer)
        }

        composable(
            route = "${JetNewsCloneDestinations.POST_DETAIL_ROUTE}/{$postIdArg}",
            arguments = listOf(
                navArgument(postIdArg) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
        ) { navBackStackEntry ->
            val postId = navBackStackEntry.arguments?.getString(postIdArg) ?: ""
            PostsDetailRoute(
                postId = postId,
                onBackClick = navController::popBackStack
            )
        }
    }
}