package io.github.mitesh.brba.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.mitesh.brba.core.database.AppDatabase.Companion.DB_VERSION
import io.github.mitesh.brba.core.database.dao.CharacterDao
import io.github.mitesh.brba.core.database.model.CharacterEntity
import io.github.mitesh.brba.core.database.util.DateTypeConverter

@Database(
    entities = [
        CharacterEntity::class,
    ],
    version = DB_VERSION,
    exportSchema = false,
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_VERSION = 1
        const val NAME = "app.db"
    }

    abstract fun characterDao(): CharacterDao
}