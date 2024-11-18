package io.github.mitesh.brba.core.network.retrofit

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.mitesh.brba.core.network.BuildConfig
import io.github.mitesh.brba.core.network.NetworkDataSource
import io.github.mitesh.brba.core.network.model.CharacterResponse
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

private interface BaBrApi {

    companion object {
        const val BASE_URL = "https://brba.shinhyo.workers.dev"
    }

    @GET(value = "/api/characters")
    suspend fun getCharacters(): List<CharacterResponse>

    @GET(value = "/api/characters/{id}")
    suspend fun getCharactersById(
        @Path("id") id: Long,
    ): List<CharacterResponse>
}

@Singleton
class RetrofitNetwork @Inject constructor(
    @ApplicationContext context: Context,
) : NetworkDataSource {

    private val baBrApi by lazy {
        val json = Json {
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .baseUrl(BaBrApi.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .apply {
                        if (!io.github.mitesh.brba.core.network.BuildConfig.DEBUG) return@apply
                        addInterceptor(
                            HttpLoggingInterceptor { Timber.tag("OkHttp##\t").d(it) }
                                .apply { level = HttpLoggingInterceptor.Level.BODY },
                        )
                    }
                    .cache(Cache(context.cacheDir, 5L * 1024 * 1024))
                    .addInterceptor { forceCache(it) }
                    .build(),
            )
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(BaBrApi::class.java)
    }

    private fun forceCache(it: Interceptor.Chain, day: Int = 7): Response {
        val request = it.request().newBuilder().header(
            "Cache-Control",
            "max-stale=" + 60 * 60 * 24 * day,
        ).build()
        val response = it.proceed(request)
        Timber.d("provideOkHttpClient: response: $response")
        Timber.i("provideOkHttpClient: cacheControl: ${response.cacheControl}")
        Timber.i("provideOkHttpClient: networkResponse: ${response.networkResponse}")
        return response
    }

    override suspend fun getCharacter(): List<CharacterResponse> =
        baBrApi.getCharacters()

    override suspend fun getCharacter(id: Long): List<CharacterResponse> =
        baBrApi.getCharactersById(id)
}