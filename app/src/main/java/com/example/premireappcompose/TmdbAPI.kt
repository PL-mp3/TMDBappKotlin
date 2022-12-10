package com.example.premireappcompose

import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbAPI{
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbResultMovies

    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbResultSeries

    @GET("trending/person/week")
    suspend fun lastpersons(@Query("api_key") api_key: String): TmdbResultPersons

    @GET("search/movie")
    suspend fun searchmovies(@Query("api_key") api_key: String, @Query("query") searchtext: String): TmdbResultMovies

    @GET("search/tv")
    suspend fun searchtv(@Query("api_key") api_key: String, @Query("query") searchtext: String): TmdbResultSeries

    @GET("movie/{id_movie}")
    suspend fun detailMovie(@Path("id_movie") movie_id: String, @Query("api_key") api_key: String): MovieDetail

    @GET("movie/{id_movie}/credits")
    suspend fun detailMovieCredits(@Path("id_movie") movie_id: String, @Query("api_key") api_key: String): CreditsMovie

    @GET("tv/{id_tv}")
    suspend fun detailTv(@Path("id_tv") tv_id: String, @Query("api_key") api_key: String): TvDetail

    @GET("tv/{id_tv}/credits")
    suspend fun detailSerieCredits(@Path("id_tv") tv_id: String, @Query("api_key") api_key: String): CreditsSerie
}
