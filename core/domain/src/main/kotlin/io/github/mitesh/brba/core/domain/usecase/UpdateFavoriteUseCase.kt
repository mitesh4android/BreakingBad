package io.github.mitesh.brba.core.domain.usecase

import io.github.mitesh.brba.core.common.di.BrbaDispatcher.IO
import io.github.mitesh.brba.core.common.di.Dispatcher
import io.github.mitesh.brba.core.domain.repository.CharactersRepository
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val repo: CharactersRepository,
) {
    operator fun invoke(character: BrbaCharacter): Flow<Boolean> = repo.updateFavorite(character)
        .flowOn(ioDispatcher)
}