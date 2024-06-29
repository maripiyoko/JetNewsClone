package app.chestnuts.jetnewsclone.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.chestnuts.jetnewsclone.R

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalDrawerSheet(modifier) {
        JetNewsCloneLogo()
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.home_title)) },
            selected = currentRoute == JetNewsCloneDestinations.HOME_ROUTE,
            onClick = { navigateToHome(); closeDrawer() },
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.interests_title)) },
            selected = currentRoute == JetNewsCloneDestinations.INTERESTS_ROUTE,
            onClick = { navigateToInterests(); closeDrawer() }
        )
    }
}

@Composable
private fun JetNewsCloneLogo(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
        )
    }
}