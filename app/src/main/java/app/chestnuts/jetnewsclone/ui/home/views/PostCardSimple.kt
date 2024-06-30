package app.chestnuts.jetnewsclone.ui.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.chestnuts.jetnewsclone.data.posts.impl.post3
import app.chestnuts.jetnewsclone.data.posts.impl.post5
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme
import coil.compose.AsyncImage

@Composable
fun PostCardSimple(
    post: Post,
    onClickPost: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .clickable { onClickPost(post.id) }
            //.background(Color.Yellow)
            //.border(1.dp, Color.Red)
    ) {
        PostThumbImage(post = post)
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.heightIn(min = 80.dp)
                //.background(Color.Yellow)
        ) {
            PostTitle(post = post)
            PostAuthorAndReadTime(post = post)
        }
    }
}

@Composable
fun PostTitle(
    post: Post,
) {
    Text(
        text = post.title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyLarge,
        //modifier = Modifier.background(Color.Cyan),
    )
}

@Composable
fun PostAuthorAndReadTime(
    post: Post,
) {
    val authorName = post.metadata.author.name
    val readTime = post.metadata.readTimeMinutes
    Text(
        text = "$authorName - $readTime min read",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodySmall,
    )
}

@Composable
fun PostThumbImage(
    post: Post,
) {
    val imageUrl = post.thumbnailImageUrl ?: "https://placehold.jp/80x80.png"
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .padding(top = 6.dp, bottom = 6.dp, start = 0.dp, end = 6.dp)
    )
}

@Composable
fun PostListDivider() {
    HorizontalDivider(

    )
}

@Preview
@Composable
private fun PostTitle_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostTitle(post = post3)
        }
    }
}

@Preview
@Composable
private fun PostAuthorAndReadTime_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostAuthorAndReadTime(post = post3)
        }
    }
}

@Preview
@Composable
private fun PostThumbImage_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostThumbImage(post = post3)
        }
    }
}

@Preview
@Composable
private fun PostCardSimple_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            PostCardSimple(post = post5, onClickPost = {})
        }
    }
}