package io.github.mitesh.brba.core.network

import androidx.annotation.Keep
import io.github.mitesh.brba.core.network.model.CharacterResponse
import retrofit2.http.Path

@Keep
interface NetworkDataSource {

    suspend fun getCharacter(): List<CharacterResponse>

    suspend fun getCharacter(@Path("id") id: Long): List<CharacterResponse>
}