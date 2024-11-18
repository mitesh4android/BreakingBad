package io.github.mitesh.brba.core.model

import java.util.Date

data class BrbaCharacter(
    val charId: Long,
    val name: String = "",
    val birthday: String = "",
    val img: String = "",
    val status: String = "",
    val nickname: String = "",
    val portrayed: String = "",
    val category: List<String> = listOf(),
    val description: String = "",
    val ratio: Float = 1f,
    val isFavorite: Boolean = false,
    val ctime: Date = Date(),
)