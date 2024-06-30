package app.chestnuts.jetnewsclone.ui.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsDetailRoute(
    postId: String,
    onBackClick: () -> Unit,
) {
    PostDetailView(
        postId,
        onBackClick
    )
}

@Composable
fun PostDetailView(
    postId: String,
    onBackClick: () -> Unit,
    viewModel: PostViewModel = koinViewModel(),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Post detail $postId")
    }
}