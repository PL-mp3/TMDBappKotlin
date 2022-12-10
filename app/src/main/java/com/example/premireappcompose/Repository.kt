package com.example.premireappcompose

import com.example.premireappcompose.DAO.FilmDao
import com.example.premireappcompose.entities.ActeurEntity
import com.example.premireappcompose.entities.FilmEntity
import com.example.premireappcompose.entities.SerieEntity

class Repository(private val tmdbAPI: TmdbAPI, private val dbFilm: FilmDao) {
    private val apikey = "d2ee8f9a0abe429c115a40452040c23a"

    // API

    suspend fun lastMovies() = tmdbAPI.lastmovies(api_key = apikey).results

    suspend fun lastSeries() = tmdbAPI.lastseries(api_key = apikey).results

    suspend fun lastPersons() = tmdbAPI.lastpersons(api_key = apikey).results

    suspend fun searchmovies(search:String) = tmdbAPI.searchmovies(api_key = apikey,searchtext = search).results

    suspend fun searchtv(search:String) = tmdbAPI.searchtv(api_key = apikey,searchtext = search).results

    suspend fun detailMovie(id:String) = tmdbAPI.detailMovie(api_key = apikey,movie_id = id)

    suspend fun detailMovieCredits(id:String) = tmdbAPI.detailMovieCredits(api_key = apikey,movie_id = id)

    suspend fun detailTv(id:String) = tmdbAPI.detailTv(api_key = apikey,tv_id = id)

    suspend fun detailSerieCredits(id:String) = tmdbAPI.detailSerieCredits(api_key = apikey,tv_id = id)

    // DB

//    FILM
    suspend fun getFavFilms() = dbFilm.getFavFilms()
    suspend fun notFavFilm(id: Int) = dbFilm.deleteFilm(id)
    suspend fun isFavFilm(movie: Movie) = dbFilm.insertFilm(FilmEntity(fiche = movie, id = movie.id.toString()))

//    SERIE
    suspend fun getFavSeries() = dbFilm.getFavSeries()
    suspend fun notFavSerie(id: Int) = dbFilm.deleteSerie(id)
    suspend fun isFavSerie(serie: Serie) = dbFilm.insertSerie(SerieEntity(fiche = serie, id = serie.id.toString()))

//    ACTEUR
    suspend fun gatFavActeurs() = dbFilm.getFavActeurs()
    suspend fun notFavActeur(id: Int) = dbFilm.deleteActeur(id)
    suspend fun isFavActeur(acteur: Person) = dbFilm.insertActeur(ActeurEntity(fiche = acteur, id = acteur.id.toString()))

}
