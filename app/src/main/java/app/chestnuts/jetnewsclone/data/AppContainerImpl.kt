package app.chestnuts.jetnewsclone.data

import android.content.Context
import app.chestnuts.jetnewsclone.data.interests.InterestsRepository
import app.chestnuts.jetnewsclone.data.interests.impl.FakeInterestsRepository
import app.chestnuts.jetnewsclone.data.posts.PostsRepository
import app.chestnuts.jetnewsclone.data.posts.impl.FakePostsRepository

interface AppContainer {
    val postsRepository: PostsRepository
    val interestsRepository: InterestsRepository
}

class AppContainerImpl(
    private val applicationContext: Context
) : AppContainer {
    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }

    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}