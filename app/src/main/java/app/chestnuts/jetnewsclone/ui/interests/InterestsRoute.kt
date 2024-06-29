package app.chestnuts.jetnewsclone.ui.interests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.chestnuts.jetnewsclone.R

@Composable
fun InterestsRoute(
    openDrawer: () -> Unit,
) {
    InterestsScreen(
        openDrawer = openDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InterestsScreen(
    openDrawer: () -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()

    Scaffold(
        topBar = {
            InterestsAppTopBar(
                openDrawer = openDrawer,
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Interests")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InterestsAppTopBar(
    openDrawer: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.interests_title))
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}