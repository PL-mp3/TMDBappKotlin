package com.example.premireappcompose

data class TmdbResultMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDetail(
    val isFav: Boolean = false,
    val adult: Boolean = false,
    val backdrop_path: String = """/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg""",
    val budget: Int = 250000000,
    val homepage: String = "",
    val id: Int = 505642,
    val imdb_id: String = """tt9114286""",
    val original_language: String = """en""",
    val original_title: String = """Black Panther: Wakanda Forever""",
    val overview: String = """Après la mort du roi T'Challa alias Black Panther, le Wakanda est en deuil et Ramonda a repris le siège royal avec l'aide de sa fille Shuri, des Dora Milaje, Okoye, Ayo et de M'Baku. Cependant, quand Namor, roi de Talocan, déclare la guerre à la nation, les personnages que nous connaissons vont devoir s'allier à de nouvelles personnes, comme Riri Williams mais aussi à d'anciennes connaissances pour vaincre cette menace.""",
    val popularity: Double = 3728.879,
    val poster_path: String = """/kxoqr19c7pxEsiOXIUZ4V7l7eWO.jpg""",
    val release_date: String = """2022-11-09""",
    val revenue: Int = 365000000,
    val runtime: Int = 162,
    val status: String = """Released""",
    val tagline: String = """Pour toujours""",
    val title: String = """Black Panther : Wakanda Forever""",
    val video: Boolean = false,
    val vote_average: Double = 7.525,
    val vote_count: Int = 671
)

data class BelongsToCollection(
    val backdrop_path: String = """/1Jj7Frjjbewb6Q6dl6YXhL3kuvL.jpg""",
    val id: Int = 529892,
    val name: String = """Black Panther - Saga""",
    val poster_path: String = """/uVnN6KnfDuHiC8rsVsSc7kk0WRD.jpg"""
)

data class Genre(
    val id: Int = 28,
    val name: String = """Action"""
)

data class ProductionCompany(
    val id: Int = 420,
    val logo_path: String = """/hUzeosd33nzE5MCNsZxCGEKTXaQ.png""",
    val name: String = """Marvel Studios""",
    val origin_country: String = """US"""
)

data class ProductionCountry(
    val iso_3166_1: String = """US""",
    val name: String = """United States of America"""
)

data class SpokenLanguage(
    val english_name: String = """English""",
    val iso_639_1: String = """en""",
    val name: String = """English"""
)

data class Movie(
    var isFav: Boolean = false,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class TmdbResultSeries(
    val page: Int,
    val results: List<Serie>,
    val total_pages: Int,
    val total_results: Int
)
data class Serie(
    var isFav: Boolean = false,
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
)

data class TmdbResultPersons(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)

data class Person(
    var isFav: Boolean = false,
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,

    )

data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
data class TvDetail(
    val adult: Boolean = false,
    val backdrop_path: String = """/ajztm40qDPqMONaSJhQ2PaNe2Xd.jpg""",
    val created_by: List<CreatedBy> = listOf(),
    val episode_run_time: List<Int> = listOf(),
    val first_air_date: String = """2022-09-21""",
    val genres: List<Genre> = listOf(),
    val homepage: String = """https://www.disneyplus.com/series/andor/3xsQKWG00GL5""",
    val id: Int = 83867,
    val in_production: Boolean = true,
    val languages: List<String> = listOf(),
    val last_air_date: String = """2022-11-16""",
    val last_episode_to_air: LastEpisodeToAir = LastEpisodeToAir(),
    val name: String = """Star Wars : Andor""",
    val networks: List<Network> = listOf(),
    val next_episode_to_air: NextEpisodeToAir = NextEpisodeToAir(),
    val number_of_episodes: Int = 12,
    val number_of_seasons: Int = 1,
    val origin_country: List<String> = listOf(),
    val original_language: String = """en""",
    val original_name: String = """Star Wars: Andor""",
    val overview: String = """En cette ère dangereuse, Cassian Andor emprunte un chemin qui fera de lui un héros de la rébellion.""",
    val popularity: Double = 798.887,
    val poster_path: String = """/1jsA4dpbSHBoDrQYVzeq53tvblG.jpg""",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val seasons: List<Season> = listOf(),
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = """Returning Series""",
    val tagline: String = """La rébellion commence.""",
    val type: String = """Scripted""",
    val vote_average: Double = 8.102,
    val vote_count: Int = 266
)

data class CreatedBy(
    val credit_id: String = """5fd2abb9b3f6f5003e41a184""",
    val gender: Int = 2,
    val id: Int = 19242,
    val name: String = """Tony Gilroy""",
    val profile_path: String = """/tjKPe7Yq68p2IaaCAdSOmXStOKq.jpg"""
)


data class LastEpisodeToAir(
    val air_date: String = """2022-11-16""",
    val episode_number: Int = 11,
    val id: Int = 3745392,
    val name: String = """La communauté des filles des colons""",
    val overview: String = """Alors qu'il est a nouveau en cavale, Cassian prépare sa prochaine action, espérant retourner sur Ferrix avant qu'il ne soit trop tard.""",
    val production_code: String = "",
    val runtime: Int = 46,
    val season_number: Int = 1,
    val show_id: Int = 83867,
    val still_path: String = """/7dfaQ4qkByhVWrHE2DE4H5Tmvtt.jpg""",
    val vote_average: Double = 7.0,
    val vote_count: Int = 5
)

data class Network(
    val id: Int = 2739,
    val logo_path: String = """/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png""",
    val name: String = """Disney+""",
    val origin_country: String = """US"""
)

data class NextEpisodeToAir(
    val air_date: String = """2022-11-23""",
    val episode_number: Int = 12,
    val id: Int = 3745393,
    val name: String = """Épisode XII""",
    val overview: String = "",
    val production_code: String = "",
    val runtime: Any = Any(),
    val season_number: Int = 1,
    val show_id: Int = 83867,
    val still_path: Any = Any(),
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)



data class Season(
    val air_date: String = """2022-09-20""",
    val episode_count: Int = 12,
    val id: Int = 112257,
    val name: String = """Saison 1""",
    val overview: String = "",
    val poster_path: String = """/59SVNwLfoMnZPPB6ukW6dlPxAdI.jpg""",
    val season_number: Int = 1
)
data class CreditsMovie(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 505642
)

data class CreditsSerie(
    val cast: List<CastSerie> = listOf(),
    val crew: List<CrewSerie> = listOf(),
    val id: Int = 90669
)

data class CastSerie(
    val adult: Boolean = false,
    val character: String = """Maura Franklin""",
    val credit_id: String = """629e5e9b12197e1689f28b4f""",
    val gender: Int = 1,
    val id: Int = 130414,
    val known_for_department: String = """Acting""",
    val name: String = """Emily Beecham""",
    val order: Int = 0,
    val original_name: String = """Emily Beecham""",
    val popularity: Double = 55.884,
    val profile_path: String = ""
)

data class CrewSerie(
    val adult: Boolean = false,
    val credit_id: String = """5d139f06c3a368573021ec26""",
    val department: String = """Production""",
    val gender: Int = 1,
    val id: Int = 1075096,
    val job: String = """Executive Producer""",
    val known_for_department: String = """Writing""",
    val name: String = """Jantje Friese""",
    val original_name: String = """Jantje Friese""",
    val popularity: Double = 15.463,
    val profile_path: String = ""
)

data class Cast(
    val adult: Boolean = false,
    val cast_id: Int = 4,
    val character: String = """Shuri""",
    val credit_id: String = """5a95b93292514154f7004c22""",
    val gender: Int = 1,
    val id: Int = 1083010,
    val known_for_department: String = """Acting""",
    val name: String = """Letitia Wright""",
    val order: Int = 0,
    val original_name: String = """Letitia Wright""",
    val popularity: Double = 116.787,
    val profile_path: String = ""
)

data class Crew(
    val adult: Boolean = false,
    val credit_id: String = """60326570befb09003e8ff17d""",
    val department: String = """Production""",
    val gender: Int = 1,
    val id: Int = 7232,
    val job: String = """Casting""",
    val known_for_department: String = """Production""",
    val name: String = """Sarah Halley Finn""",
    val original_name: String = """Sarah Halley Finn""",
    val popularity: Double = 9.08,
    val profile_path: String = ""
)

