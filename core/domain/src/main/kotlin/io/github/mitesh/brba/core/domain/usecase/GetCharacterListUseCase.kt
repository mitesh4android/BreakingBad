package io.github.mitesh.brba.core.domain.usecase

import io.github.mitesh.brba.core.common.di.BrbaDispatcher
import io.github.mitesh.brba.core.common.di.Dispatcher
import io.github.mitesh.brba.core.domain.repository.CharactersRepository
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

class GetCharacterListUseCase @Inject constructor(
    @Dispatcher(BrbaDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val repo: CharactersRepository,
) {
    companion object {
        private const val MIN_RATIO = 1.4f
    }

    private val random: Random by lazy { Random(Calendar.getInstance().timeInMillis) }

    operator fun invoke(): Flow<List<BrbaCharacter>> = repo.getCharacterList()
        .map {
            it.map { i -> i.copy(ratio = MIN_RATIO + random.nextInt(4) * 0.12f) }
        }
        .combine(
            repo.getDatabaseList(),
        ) { listApi, listDb ->
            listApi.map { item ->
                item.copy(
                    isFavorite = listDb.find { it.charId == item.charId }?.isFavorite ?: false,
                )
            }
        }
        .flowOn(ioDispatcher)
}