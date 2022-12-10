package com.example.premireappcompose.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.premireappcompose.entities.ActeurEntity
import com.example.premireappcompose.entities.FilmEntity
import com.example.premireappcompose.entities.SerieEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentity")
    suspend fun getFavFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmEntity)

    @Query("DELETE FROM filmentity WHERE id = :id")
    suspend fun deleteFilm(id: kotlin.Int)

    @Query("SELECT * FROM serieentity")
    suspend fun getFavSeries(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSerie(serie: SerieEntity)

    @Query("DELETE FROM serieentity WHERE id = :id")
    suspend fun deleteSerie(id: kotlin.Int)

    @Query("SELECT * FROM acteurentity")
    suspend fun getFavActeurs(): List<ActeurEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActeur(serie: ActeurEntity)

    @Query("DELETE FROM acteurentity WHERE id = :id")
    suspend fun deleteActeur(id: kotlin.Int)

}