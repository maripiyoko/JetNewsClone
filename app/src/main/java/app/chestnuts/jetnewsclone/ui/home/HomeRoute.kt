package app.chestnuts.jetnewsclone.ui.home

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
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.chestnuts.jetnewsclone.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    openDrawer: () -> Unit,
) {
    HomeScreen(openDrawer = openDrawer)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    openDrawer: () -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()

    Scaffold(
        topBar = {
            HomeAppTopBar(
                openDrawer = openDrawer,
                topAppBarState = topAppBarState,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Home")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppTopBar(
    openDrawer: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
    topAppBarState: TopAppBarState = rememberTopAppBarState(),
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.home_title))
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}