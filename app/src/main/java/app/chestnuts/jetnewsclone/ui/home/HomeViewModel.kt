package app.chestnuts.jetnewsclone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chestnuts.jetnewsclone.data.Result
import app.chestnuts.jetnewsclone.data.posts.PostsRepository
import app.chestnuts.jetnewsclone.model.PostsFeed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val postsRepository: PostsRepository,
): ViewModel() {
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
        )
    )

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = viewModelState.value.toUiState()
        )

    init {
        refreshPosts()
    }

    private fun refreshPosts() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = postsRepository.getPostsFeed()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
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

sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: List<String>

    data class NoPosts(
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
    ): HomeUiState

    data class HasPosts(
        val postsFeed: PostsFeed,
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
    ): HomeUiState
}

private data class HomeViewModelState(
    val postsFeed: PostsFeed? = null,
    val isLoading: Boolean,
    val errorMessages: List<String> = emptyList()
) {
    fun toUiState(): HomeUiState =
        if (postsFeed == null) {
            HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            HomeUiState.HasPosts(
                postsFeed = postsFeed,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}