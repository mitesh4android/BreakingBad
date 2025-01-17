package io.github.mitesh.brba.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mitesh.brba.core.common.result.Result
import io.github.mitesh.brba.core.common.result.asResult
import io.github.mitesh.brba.core.domain.usecase.GetCharacterUseCase
import io.github.mitesh.brba.core.domain.usecase.UpdateFavoriteUseCase
import io.github.mitesh.brba.core.model.BrbaCharacter
import io.github.mitesh.brba.feature.detail.navigaion.Detail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed class DetailInfoState {
    data object Loading : DetailInfoState()
    data class Success(val character: BrbaCharacter) : DetailInfoState()
    data class Error(val exception: Throwable) : DetailInfoState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
) : ViewModel() {

    private val args = savedStateHandle.toRoute<io.github.mitesh.brba.feature.detail.navigaion.Detail>()

    private val _imageState = MutableStateFlow(args.id to args.image)
    val imageState = _imageState.asStateFlow()

    val infoState = getCharacterUseCase(id = args.id)
        .asResult()
        .map {
            when (it) {
                is Result.Loading -> DetailInfoState.Loading
                is Result.Success -> {
                    DetailInfoState.Success(character = it.data)
                }

                is Result.Error -> DetailInfoState.Error(exception = it.exception)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailInfoState.Loading,
        )

    fun updateFavorite(character: BrbaCharacter) {
        updateFavoriteUseCase(character)
            .catch { e -> e.printStackTrace() }
            .launchIn(viewModelScope)
    }
}