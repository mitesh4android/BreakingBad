package io.github.mitesh.brba.core.network.model

import androidx.annotation.Keep
import io.github.mitesh.brba.core.model.BrbaCharacter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CharacterResponse(
    @SerialName("char_id")
    val charId: Long?,
    val name: String?,
    val birthday: String?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
    val description: String?,
)

fun CharacterResponse.asExternalModel(): BrbaCharacter = BrbaCharacter(
    charId = charId ?: throw NullPointerException(),
    name = name ?: "",
    birthday = birthday ?: "",
    img = img ?: "",
    status = status ?: "",
    nickname = nickname ?: "",
    portrayed = portrayed ?: "",
    category = category?.split(",").orEmpty().map { it.trim() },
    description = description ?: "",
)