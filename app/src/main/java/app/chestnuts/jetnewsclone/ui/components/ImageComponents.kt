package app.chestnuts.jetnewsclone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.chestnuts.jetnewsclone.R
import app.chestnuts.jetnewsclone.ui.theme.JetNewsCloneAppTheme
import coil.compose.AsyncImage

@Composable
fun CircleLogoImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val sizedModifier = modifier
        .size(48.dp)
        .clip(CircleShape)

    if (imageUrl.isEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.dragon),
            contentDescription = "dummy logo",
            modifier = sizedModifier,
        )
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = sizedModifier,
        )
    }
}

@Preview
@Composable
private fun CircleLogoImage_Preview() {
    JetNewsCloneAppTheme {
        Surface {
            CircleLogoImage(imageUrl = "")
        }
    }
}