package com.jnasif.moviegallery.utilities

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntArray(value : IntArray?) : String? {
        return value?.joinToString(separator = ",")
    }
    @TypeConverter
    fun toIntArray(value: String?): IntArray?{
        return value?.split(",")?.mapNotNull { it.toIntOrNull()}?.toIntArray()
    }
}