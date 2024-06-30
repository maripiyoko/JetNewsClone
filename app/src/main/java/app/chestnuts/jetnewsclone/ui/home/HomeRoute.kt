package app.chestnuts.jetnewsclone.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.chestnuts.jetnewsclone.R
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.model.PostsFeed
import app.chestnuts.jetnewsclone.ui.home.views.PostCardHighlighted
import app.chestnuts.jetnewsclone.ui.home.views.PostCardSimple
import app.chestnuts.jetnewsclone.ui.home.views.PostListDivider
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
                        HomeWithPostFeeds(
                            postsFeed = state.postsFeed,
                            onClickAction = viewModel::onClickPost,
                        )
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
private fun HomeWithPostFeeds(
    postsFeed: PostsFeed,
    onClickAction: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        HomeHeaderText(text = "Top stories for you")
        HomeHighlightedPost(
            post = postsFeed.highlightedPost,
            onClickAction = onClickAction
        )
        HomeRecommendedPosts(
            postsFeed = postsFeed,
            onClickAction = onClickAction
        )
    }
}

@Composable
fun HomeHeaderText(
    text: String,
) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Clip,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
private fun HomeRecommendedPosts(
    postsFeed: PostsFeed,
    onClickAction: (String) -> Unit,
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        state = state,
    ) {
        item {
            PostListDivider()
        }
        items(postsFeed.recommendedPosts) { post ->
            PostCardSimple(
                post = post,
                onClickPost = onClickAction
            )
            PostListDivider()
        }
    }
}

@Composable
private fun HomeHighlightedPost(
    post: Post,
    onClickAction: (String) -> Unit,
) {
    PostCardHighlighted(post, onClickAction)
}