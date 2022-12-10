package com.example.premireappcompose

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.premireappcompose.DAO.FilmDao
import com.example.premireappcompose.entities.ActeurEntity
import com.example.premireappcompose.entities.FilmEntity
import com.example.premireappcompose.entities.SerieEntity

@Database(entities = [FilmEntity::class, SerieEntity::class, ActeurEntity::class], version = 8)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}