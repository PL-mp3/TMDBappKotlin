package com.example.premireappcompose

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    private val filmJsonadapter = moshi.adapter(Movie::class.java)
    private val serieJsonadapter = moshi.adapter(Serie::class.java)
    private val acteurJsonadapter = moshi.adapter(Person::class.java)

    @TypeConverter
    fun StringToTmdbMovie(value: String): Movie? {
        return filmJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbMovieToString(film: Movie): String {
        return filmJsonadapter.toJson(film)
    }

    @TypeConverter
    fun StringToTmdbSerie(value: String): Serie? {
        return serieJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbSerieToString(serie: Serie): String {
        return serieJsonadapter.toJson(serie)
    }

    @TypeConverter
    fun StringToTmdbActeur(value: String): Person? {
        return acteurJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbActeurToString(acteur: Person): String {
        return acteurJsonadapter.toJson(acteur)
    }
}