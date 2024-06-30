package app.chestnuts.jetnewsclone.ui.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.chestnuts.jetnewsclone.R
import app.chestnuts.jetnewsclone.data.posts.impl.post2
import app.chestnuts.jetnewsclone.data.posts.impl.publication
import app.chestnuts.jetnewsclone.model.Paragraph
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.model.Publication
import app.chestnuts.jetnewsclone.ui.components.CircleLogoImage
import app.chestnuts.jetnewsclone.ui.home.views.PostWideImage
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme
import org.koin.androidx.compose.koinViewModel

private val defaultSpacerSize = 16.dp

@Composable
fun PostsDetailRoute(
    postId: String,
    onBackClick: () -> Unit,
) {
    PostDetailScreen(
        postId,
        onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: String,
    onBackClick: () -> Unit,
    viewModel: PostViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.loadPost(postId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val publication by viewModel.postPublication.collectAsStateWithLifecycle()

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            PostDetailAppTopBar(
                onBackClick = onBackClick,
                publication = publication,
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            PostDetailBottomBar(

            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            uiState.let { state ->
                when(state) {
                    is PostUiState.NotFound -> {
                        PostEmptyView()
                    }
                    is PostUiState.FoundPost -> {
                        PostContent(
                            post = state.post,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .nestedScroll(scrollBehavior.nestedScrollConnection)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostDetailAppTopBar(
    onBackClick: () -> Unit,
    publication: Publication,
    scrollBehavior: TopAppBarScrollBehavior?,
) {
    val title = publication.name
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //modifier = Modifier.border(1.dp, Color.Red)
            ) {
                CircleLogoImage(publication.logoUrl)
                Column(
                    verticalArrangement = Arrangement.Center,
                    //modifier = Modifier.background(Color.Yellow)
                ) {
                    Text(text = "Published in:", style = MaterialTheme.typography.titleSmall)
                    Text(text = title, style = MaterialTheme.typography.titleSmall)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PostDetailAppTopBar_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostDetailAppTopBar(
                onBackClick = { },
                publication = publication,
                scrollBehavior = null,
            )
        }
    }
}

@Composable
fun PostDetailBottomBar(

) {
    BottomAppBar {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Default.ThumbUp,
                contentDescription = "good",
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "favorite",
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.Share,
                contentDescription = "share",
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = "text",
            )
        }
    }
}

@Preview
@Composable
private fun PostDetailBottomBar_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostDetailBottomBar()
        }
    }
}

@Composable
private fun PostEmptyView(

) {
    Text("no post found")
}

@Composable
private fun PostContent(
    post: Post,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state,
    ) {
        item {
            PostWideImage(post)
            Spacer(modifier = Modifier.height(defaultSpacerSize))
            Text(post.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(defaultSpacerSize))
            if (post.subtitle != null) {
                Text(post.subtitle, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(defaultSpacerSize))
            }
        }
        item {
            PostMetadata(post)
        }
        items(post.paragraphs) { ParagraphView(it) }
    }
}

@Composable
private fun ParagraphView(
    paragraph: Paragraph,
) {
    // TODO
    Text(text = paragraph.text)
}

@Composable
private fun PostMetadata(
    post: Post,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier = Modifier.size(defaultSpacerSize))
        Column {
            Text(
                text = post.metadata.author.name,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(
                    id = R.string.article_post_min_read,
                    formatArgs = arrayOf(
                        post.metadata.date,
                        post.metadata.readTimeMinutes,
                    )
                ),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun PostMetadata_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostMetadata(post2)
        }
    }
}