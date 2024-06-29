package app.chestnuts.jetnewsclone.data.posts

import app.chestnuts.jetnewsclone.data.Result
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.model.PostsFeed

interface PostsRepository {
    suspend fun getPost(postId: String?): Result<Post>

    suspend fun getPostsFeed(): Result<PostsFeed>
}