package app.chestnuts.jetnewsclone.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chestnuts.jetnewsclone.data.Result
import app.chestnuts.jetnewsclone.data.posts.PostsRepository
import app.chestnuts.jetnewsclone.model.Post
import app.chestnuts.jetnewsclone.model.Publication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
    private val postsRepository: PostsRepository,
): ViewModel() {
    private val viewModelState = MutableStateFlow(
        PostDetailViewModelState(
            isLoading = true,
        )
    )

    val uiState = viewModelState
        .map(PostDetailViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = viewModelState.value.toUiState(),
        )

    val postPublication : StateFlow<Publication> = viewModelState
        .map(PostDetailViewModelState::toUiState)
        .filterIsInstance<PostUiState.FoundPost>()
        .map { it.post.publication }
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Publication.empty(),
        )

    fun loadPost(postId: String) {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = postsRepository.getPost(postId)
            viewModelState.update {
                when (result) {
                    is Result.Success -> {
                        it.copy(post = result.data, isLoading = false)
                    }
                    is Result.Error -> {
                        val newErrorMessage = "loading error"
                        val errorMessages = it.errorMessages + newErrorMessage
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }
}

sealed interface PostUiState {
    val isLoading: Boolean
    val errorMessages: List<String>

    data class NotFound(
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
    ): PostUiState

    data class FoundPost(
        val post: Post,
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
    ): PostUiState
}

private data class PostDetailViewModelState(
    val post: Post? = null,
    val isLoading: Boolean,
    val errorMessages: List<String> = emptyList(),
) {
    fun toUiState(): PostUiState =
        if (post == null) {
            PostUiState.NotFound(
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        } else {
            PostUiState.FoundPost(
                post = post,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}