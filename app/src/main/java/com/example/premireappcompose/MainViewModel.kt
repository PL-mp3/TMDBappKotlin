package com.example.premireappcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premireappcompose.entities.ActeurEntity
import com.example.premireappcompose.entities.FilmEntity
import com.example.premireappcompose.entities.SerieEntity
import com.example.premireappcompose.models.*
import com.example.premireappcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val favMovies = MutableStateFlow<List<FilmEntity>>(listOf())

    val series = MutableStateFlow<List<Serie>>(listOf())
    val favSeries = MutableStateFlow<List<SerieEntity>>(listOf())

    val persons = MutableStateFlow<List<Person>>(listOf())
    val favActeurs = MutableStateFlow<List<ActeurEntity>>(listOf())


    fun affichLastMovies(){
            viewModelScope.launch {
                movies.value = repo.lastMovies()
                affichFavMovies()
            }
    }

    fun affichFavMovies(){
        viewModelScope.launch {
            favMovies.value = repo.getFavFilms()
        }
    }

    fun affichLastSeries(){
            viewModelScope.launch { series.value = repo.lastSeries() }
    }

    fun affichFavSeries(){
        viewModelScope.launch {
            favSeries.value = repo.getFavSeries()
        }
    }

    fun affichLastPersons(){
            viewModelScope.launch { persons.value = repo.lastPersons() }
    }

    fun affichFavActeurs(){
        viewModelScope.launch {
            favActeurs.value = repo.gatFavActeurs()
        }
    }

    fun affichSearchMovies(search:String){

        if(search.length>0) {
            viewModelScope.launch { movies.value = repo.searchmovies(search) }
        }
    }

    fun affichSearchTv(search:String){
        if(search.length>0){
            viewModelScope.launch { series.value = repo.searchtv(search) }
        }
    }

    var movieDetail = MutableStateFlow(MovieDetail())

    fun affichDetail(id:String){
            viewModelScope.launch { movieDetail.value = repo.detailMovie(id) }
    }

    var movieCredits = MutableStateFlow(CreditsMovie())

    fun affichDetailMovieCredits(id:String){
            viewModelScope.launch { movieCredits.value = repo.detailMovieCredits(id) }
    }

    var tvDetail = MutableStateFlow(TvDetail())

    fun affichDetailTv(id:String){
            viewModelScope.launch { tvDetail.value = repo.detailTv(id) }
    }

    var tvCredits = MutableStateFlow(CreditsSerie())

    fun affichDetailTvCredits(id:String){
            viewModelScope.launch { tvCredits.value = repo.detailSerieCredits(id) }
    }

    fun addFavFilm(movie: Movie){
        viewModelScope.launch {
            repo.isFavFilm(movie);
            val favMovies = repo.getFavFilms()
            val newListMovies = mutableListOf<Movie>()
            movies.value.map { movie ->
                if(favMovies.any{isFavMovie ->
                        isFavMovie.id == movie.id.toString();
                    }){
                    val newFavMovie = movie.copy(isFav = true)
                    newListMovies.add(newFavMovie)
                }else{
                    newListMovies.add(movie)
                }
            }
            Log.i("NEW FAV", favMovies.toString())
            movies.value = newListMovies
            affichFavMovies()
        }
    }

    fun deleteFavFilm(movie: Movie){
        viewModelScope.launch {
            repo.notFavFilm(movie.id);
            val favMovies = repo.getFavFilms()

            val newListMovies = mutableListOf<Movie>()

            movies.value.map { movie ->
                if(favMovies.any{isFav ->
                        isFav.id == movie.id.toString()
                    }){
                    newListMovies.add(movie.copy(isFav = true))
                }else{
                    newListMovies.add(movie.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favMovies.toString())

            movies.value = newListMovies
            affichFavMovies()
        }
    }

    fun addFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.isFavSerie(serie);
            val favSeries = repo.getFavSeries()
            val newListSeries = mutableListOf<Serie>()
            series.value.map { serie ->
                if(favSeries.any{isFavSerie ->
                        isFavSerie.id == serie.id.toString();
                    }){
                    val newFavSerie = serie.copy(isFav = true)
                    newListSeries.add(newFavSerie)
                }else{
                    newListSeries.add(serie)
                }
            }
            Log.i("NEW FAV", favSeries.toString())
            series.value = newListSeries
            affichFavSeries()
        }
    }

    fun deleteFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.notFavSerie(serie.id);
            val favSeries = repo.getFavSeries()

            val newListSeries = mutableListOf<Serie>()

            series.value.map { serie ->
                if(favSeries.any{isFav ->
                        isFav.id == serie.id.toString()
                    }){
                    newListSeries.add(serie.copy(isFav = true))
                }else{
                    newListSeries.add(serie.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favSeries.toString())

            series.value = newListSeries
            affichFavSeries()
        }
    }

    fun addFavActeur(acteur: Person){
        viewModelScope.launch {
            repo.isFavActeur(acteur);
            val favActeurs = repo.gatFavActeurs()
            val newListActeurs = mutableListOf<Person>()
            persons.value.map { acteur ->
                if(favActeurs.any{isFavActeur ->
                        isFavActeur.id == acteur.id.toString();
                    }){
                    val newFavActeur = acteur.copy(isFav = true)
                    newListActeurs.add(newFavActeur)
                }else{
                    newListActeurs.add(acteur)
                }
            }
            Log.i("NEW FAV", favActeurs.toString())
            persons.value = newListActeurs
            affichFavActeurs()
        }
    }

    fun deleteFavActeur(acteur: Person){
        viewModelScope.launch {
            repo.notFavActeur(acteur.id);
            val favActeurs = repo.gatFavActeurs()

            val newListActeurs = mutableListOf<Person>()

            persons.value.map { acteur ->
                if(favActeurs.any{isFav ->
                        isFav.id == acteur.id.toString()
                    }){
                    newListActeurs.add(acteur.copy(isFav = true))
                }else{
                    newListActeurs.add(acteur.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favSeries.toString())
            persons.value = newListActeurs
            affichFavActeurs()
        }
    }


    init{
        affichFavMovies()
        affichFavSeries()
        affichFavActeurs()
        affichLastMovies()
        affichLastPersons()
        affichLastSeries()
    }


}