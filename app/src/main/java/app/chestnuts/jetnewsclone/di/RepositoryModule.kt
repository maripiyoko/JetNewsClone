package app.chestnuts.jetnewsclone.di

import app.chestnuts.jetnewsclone.data.interests.InterestsRepository
import app.chestnuts.jetnewsclone.data.interests.impl.FakeInterestsRepository
import app.chestnuts.jetnewsclone.data.posts.PostsRepository
import app.chestnuts.jetnewsclone.data.posts.impl.FakePostsRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<PostsRepository> { FakePostsRepository() }
    single<InterestsRepository> { FakeInterestsRepository() }
}
