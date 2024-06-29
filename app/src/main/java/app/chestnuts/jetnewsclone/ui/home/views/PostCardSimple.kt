package app.chestnuts.jetnewsclone.ui.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp)
            .clickable { onClickPost(post.id) }
            //.background(Color.Yellow)
            //.border(1.dp, Color.Red)
    ) {
        PostThumbImage(post = post)
        Column {
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
        //modifier = Modifier.background(Color.Yellow)
    )
}

@Composable
fun PostAuthorAndReadTime(
    post: Post,
) {
    val authorName = post.metadata.author.name
    val readTime = post.metadata.readTimeMinutes
    Text(text = "$authorName - $readTime min read")
}

@Composable
fun PostThumbImage(
    post: Post,
) {
    val imageUrl = post.thumbnailImageUrl.let {
        it
    } ?: "https://placehold.jp/80x80.png"
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
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