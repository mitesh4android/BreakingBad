package io.github.mitesh.brba.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.mitesh.brba.core.database.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: CharacterEntity): Long

    @Query(
        """
            SELECT * FROM ${CharacterEntity.TABLE_NAME}   
        """,
    )
    fun getAll(): Flow<List<CharacterEntity>>

    @Query(
        """
            SELECT * FROM ${CharacterEntity.TABLE_NAME} t
            WHERE t.charId=:charId
            ORDER BY t.ctime
        """,
    )
    fun getCharacter(charId: Long): Flow<CharacterEntity?>

    @Query(
        """
            SELECT * FROM ${CharacterEntity.TABLE_NAME} t
            WHERE t.favorite = 1
            ORDER BY CASE WHEN :isAsc = 1 THEN t.ctime END ASC,
            CASE WHEN :isAsc = 0 THEN t.ctime END DESC
        """,
    )
    fun getCharacter(isAsc: Boolean = true): Flow<List<CharacterEntity>>
}