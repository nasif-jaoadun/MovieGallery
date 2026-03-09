package com.jnasif.moviegallery.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jnasif.moviegallery.utilities.Converters

@Database(entities = [MovieDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDetailsDao

    companion object {
        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getDatabase(context : Context): MovieDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movies.db").build()
                    context.applicationContext
                }
            }
            return INSTANCE!!
        }
    }
}