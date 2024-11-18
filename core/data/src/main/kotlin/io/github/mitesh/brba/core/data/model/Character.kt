package io.github.mitesh.brba.core.data.model

import androidx.annotation.Keep
import io.github.mitesh.brba.core.database.model.CharacterEntity
import io.github.mitesh.brba.core.model.BrbaCharacter

@Keep
fun BrbaCharacter.asEntity() = CharacterEntity(
    charId = charId,
    name = name,
    img = img,
    nickname = nickname,
)