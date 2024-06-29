package app.chestnuts.jetnewsclone.ui.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.chestnuts.jetnewsclone.model.Post

@Composable
fun PostCardSimple(
    post: Post,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.background(Color.Yellow).border(1.dp, Color.Red)
    ) {
        Text(text = post.title)
        post.subtitle?.let { subtitle ->
            Text(text = subtitle)
        }
    }

}