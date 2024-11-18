package io.github.mitesh.brba.core.domain.usecase

import io.github.mitesh.brba.core.common.di.BrbaDispatcher
import io.github.mitesh.brba.core.common.di.Dispatcher
import io.github.mitesh.brba.core.domain.repository.CharactersRepository
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    @Dispatcher(BrbaDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val repo: CharactersRepository,
) {
    operator fun invoke(
        id: Long,
    ): Flow<BrbaCharacter> = repo.getCharacterList(id = id)
        .combine(
            repo.getDatabaseList(id = id),
        ) { api: BrbaCharacter, db: BrbaCharacter? ->
            api.copy(isFavorite = db?.isFavorite ?: false)
        }.flowOn(ioDispatcher)
}