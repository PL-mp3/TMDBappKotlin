package com.example.premireappcompose.models



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


data class CreditsMovie(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 505642
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

