package io.github.mitesh.brba.core.data.repository

import io.github.mitesh.brba.core.data.model.asEntity
import io.github.mitesh.brba.core.database.dao.CharacterDao
import io.github.mitesh.brba.core.database.model.CharacterEntity
import io.github.mitesh.brba.core.database.model.asExternalModel
import io.github.mitesh.brba.core.domain.repository.CharactersRepository
import io.github.mitesh.brba.core.model.BrbaCharacter
import io.github.mitesh.brba.core.network.NetworkDataSource
import io.github.mitesh.brba.core.network.model.CharacterResponse
import io.github.mitesh.brba.core.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class CharactersRepositoryImpl @Inject constructor(
    private val api: NetworkDataSource,
    private val dao: CharacterDao,
) : CharactersRepository {

    override fun getCharacterList(): Flow<List<BrbaCharacter>> = flow { emit(api.getCharacter()) }
        .map { it.map(CharacterResponse::asExternalModel) }

    override fun getCharacterList(id: Long): Flow<BrbaCharacter> =
        flow { emit(api.getCharacter(id)) }
            .map { it.first().asExternalModel() }

    override fun getDatabaseList(isAsc: Boolean): Flow<List<BrbaCharacter>> =
        dao.getCharacter(isAsc = isAsc)
            .map { it.map(CharacterEntity::asExternalModel) }

    override fun getDatabaseList(id: Long): Flow<BrbaCharacter?> = dao.getCharacter(charId = id)
        .map { it?.asExternalModel() }

    override fun updateFavorite(character: BrbaCharacter): Flow<Boolean> = flowOf(character)
        .map { it.asEntity().copy(favorite = !character.isFavorite) }
        .map { dao.insert(it) }
        .map { it != 0L }
}