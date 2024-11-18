package io.github.mitesh.brba.core.domain.repository

import androidx.annotation.Keep
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.coroutines.flow.Flow

@Keep
interface CharactersRepository {

    fun getCharacterList(): Flow<List<BrbaCharacter>>

    fun getCharacterList(id: Long): Flow<BrbaCharacter>

    fun getDatabaseList(isAsc: Boolean = true): Flow<List<BrbaCharacter>>

    fun getDatabaseList(id: Long): Flow<BrbaCharacter?>

    fun updateFavorite(character: BrbaCharacter): Flow<Boolean>
}