package app.chestnuts.jetnewsclone.ui.home.views

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.chestnuts.jetnewsclone.data.posts.impl.post1
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme
import coil.compose.AsyncImage

@Composable
fun PostCardHighlighted(
    post: Post,
    onClickAction: (String) -> Unit,
) {
    Column(
        modifier = Modifier.clickable { onClickAction(post.id) }
    ) {
        PostWideImage(post)
        PostWideTitle(post)
        PostWideAuthorAndReadTime(post)
        Spacer(modifier = Modifier.size(8.dp))
    }
}
@Composable
fun PostWideTitle(
    post: Post,
) {
    Text(
        text = post.title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Preview
@Composable
fun PostWideTitle_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostWideTitle(post = post1)
        }
    }
}

@Composable
fun PostWideAuthorAndReadTime(
    post: Post,
) {
    Column {
        Text(
            text = post.metadata.author.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Text(
            text = "${post.metadata.date} - ${post.metadata.readTimeMinutes} min read",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun PostWideAuthorAndReadTime_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostWideAuthorAndReadTime(post = post1)
        }
    }
}

@Composable
fun PostWideImage(
    post: Post,
) {
    val imageUrl = post.imageUrl ?: "https://placehold.jp/300x150.png"
    val description = post.title
    AsyncImage(
        model = imageUrl,
        contentDescription = description,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp),
    )
}

@Preview
@Composable
private fun PostWideImage_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostWideImage(post = post1)
        }
    }
}