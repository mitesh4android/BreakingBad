package io.github.mitesh.brba.core.database.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.mitesh.brba.core.database.model.CharacterEntity.Companion.TABLE_NAME
import io.github.mitesh.brba.core.model.BrbaCharacter
import java.util.Date

@Keep
@Entity(
    tableName = TABLE_NAME,
    indices = [(Index(value = ["charId"], unique = true))],
)
data class CharacterEntity(
    @PrimaryKey
    val charId: Long,
    val name: String,
    val img: String = "",
    val nickname: String,
    val favorite: Boolean = false,
    var ctime: Date = Date(),
) {

    companion object {
        const val TABLE_NAME = "character"
    }
}

fun CharacterEntity.asExternalModel() = BrbaCharacter(
    charId = charId,
    name = name,
    img = img,
    nickname = nickname,
    isFavorite = favorite,
    ctime = ctime,
)