package io.github.mitesh.brba.core.database.util

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun toLong(value: Date?): Long? = value?.time
}