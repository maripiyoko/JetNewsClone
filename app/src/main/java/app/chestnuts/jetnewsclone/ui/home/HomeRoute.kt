package app.chestnuts.jetnewsclone.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.chestnuts.jetnewsclone.R
import app.chestnuts.jetnewsclone.model.PostsFeed
import app.chestnuts.jetnewsclone.ui.home.views.PostCardSimple
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
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val topAppBarState = rememberTopAppBarState()

    Scaffold(
        topBar = {
            HomeAppTopBar(
                openDrawer = openDrawer,
                topAppBarState = topAppBarState,
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            uiState.let { state ->
                when(state) {
                    is HomeUiState.NoPosts -> {
                        HomeEmptyView()
                    }
                    is HomeUiState.HasPosts -> {
                        HomePostsView(state.postsFeed)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppTopBar(
    openDrawer: () -> Unit,
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

@Composable
private fun HomeEmptyView(

) {
    Text(text = "no posts")
}

@Composable
private fun HomePostsView(
    postsFeed: PostsFeed,
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        contentPadding = PaddingValues(2.dp),
        state = state,
    ) {
        items(postsFeed.allPosts) { post ->
            PostCardSimple(post)
        }
    }
}