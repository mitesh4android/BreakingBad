package io.github.mitesh.brba.feature.favorate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mitesh.brba.core.common.result.Result
import io.github.mitesh.brba.core.common.result.asResult
import io.github.mitesh.brba.core.domain.usecase.GetFavoriteListUseCase
import io.github.mitesh.brba.core.domain.usecase.UpdateFavoriteUseCase
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface FavoriteUiState {
    data class Success(
        val list: List<BrbaCharacter>,
    ) : FavoriteUiState

    data object Empty : FavoriteUiState
    data class Error(val exception: Throwable? = null) : FavoriteUiState
    data object Loading : FavoriteUiState
}

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getFavoriteListUseCase: GetFavoriteListUseCase,
    val updateFavoriteUseCase: UpdateFavoriteUseCase,
) : ViewModel() {

    val uiState = getFavoriteListUseCase()
        .asResult()
        .map {
            when (it) {
                is Result.Loading -> FavoriteUiState.Loading
                is Result.Success -> {
                    if (it.data.isEmpty()) {
                        FavoriteUiState.Empty
                    } else {
                        FavoriteUiState.Success(it.data)
                    }
                }

                is Result.Error -> FavoriteUiState.Error(it.exception)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoriteUiState.Loading,
        )

    fun onFavoriteClick(character: BrbaCharacter) {
        updateFavoriteUseCase(character)
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }
}