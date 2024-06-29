package app.chestnuts.jetnewsclone.ui.home

import androidx.lifecycle.ViewModel
import app.chestnuts.jetnewsclone.data.posts.PostsRepository

class HomeViewModel(
    private val postsRepository: PostsRepository,
): ViewModel() {
}