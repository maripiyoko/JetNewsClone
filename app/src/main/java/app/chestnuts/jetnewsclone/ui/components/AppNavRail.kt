package app.chestnuts.jetnewsclone.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.chestnuts.jetnewsclone.R
import app.chestnuts.jetnewsclone.ui.JetNewsCloneDestinations

@Composable
fun AppNavRail(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        header = {
            Icon(
                Icons.Default.Home,
                contentDescription = null
            )
        },
        modifier = modifier,
    ) {
        NavigationRailItem(
            selected = currentRoute == JetNewsCloneDestinations.HOME_ROUTE,
            onClick = navigateToHome,
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text(text = stringResource(id = R.string.home_title)) },
            alwaysShowLabel = false,
        )
        NavigationRailItem(
            selected = currentRoute == JetNewsCloneDestinations.INTERESTS_ROUTE,
            onClick = navigateToInterests,
            icon = { Icon(Icons.Default.Face, null) },
            label = { Text(text = stringResource(id = R.string.interests_title)) },
            alwaysShowLabel = false,
        )
    }
}