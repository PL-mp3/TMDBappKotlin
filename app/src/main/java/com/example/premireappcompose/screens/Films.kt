package com.example.premireappcompose.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.premireappcompose.MainViewModel
import com.example.premireappcompose.R
import com.example.premireappcompose.Structure
import com.example.premireappcompose.ui.theme.PremièreAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Films : Screen("films", "Films", icon = (Icons.Outlined.Movie))
    object Series : Screen("series", "Series", icon = (Icons.Outlined.OndemandVideo))
    object Acteurs : Screen("acteurs", "Acteurs", icon = (Icons.Outlined.People))
    object Detail : Screen("detail/{id}", "Detail", icon = (Icons.Outlined.Movie))
}

val items = listOf(
    Screen.Films,
    Screen.Series,
    Screen.Acteurs,
)

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Films(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()
    val favMovies by viewModel.favMovies.collectAsState()
    viewModel.affichLastMovies()

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {

        items(movies) { movie ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Log.i("ID FOCUSED", movie.id.toString())
                            navController.navigate("detail/${movie.id}")
                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(modifier = Modifier
                                    .size(50.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .background(Color(0xCC000000))
                                    .padding(5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = (movie.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center)
                                    CircularProgressIndicator(
                                        color = Color(0xFF001438),
                                        progress = 100f
                                    )
                                    CircularProgressIndicator(
                                        color = Color(
                                            red = 76,
                                            green = 175,
                                            blue = 80,
                                            alpha = 255
                                        ),
                                        progress = (movie.vote_average/10).toFloat()
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier.size(30.dp),
                                        onClick = {
                                            if (movie.isFav) {
                                                viewModel.deleteFavFilm(movie)
                                            } else {
                                                viewModel.addFavFilm(movie)
                                            }
                                        }
                                    ) {
                                        if (movie.isFav || favMovies.any { isFavMovie ->
                                                isFavMovie.id == movie.id.toString();
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        } else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)

                                            )
                                        }
                                    }
                                }
                                Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        movie.title,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailMovie(viewModel: MainViewModel, idDetail: String) {
    val detail by viewModel.movieDetail.collectAsState()
    viewModel.affichDetail(idDetail)

    val credits by viewModel.movieCredits.collectAsState()
    viewModel.affichDetailMovieCredits(idDetail)

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 120.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        item(span = {(GridItemSpan(maxCurrentLineSpan))}){
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()){
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${detail.backdrop_path}",
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.width(500.dp)
                    )
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = detail.title,
                        style = MaterialTheme.typography.h3,
                    )
                    Text(
                        text = "Synopsis",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    )
                    Text(
                        text = detail.overview,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    )
                    Text(
                        text = "Tête d'affiche",
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
        items(credits.cast) { acteur ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {

                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = (if(acteur.profile_path === null){
                                        "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png"
                                    }else{
                                        "https://image.tmdb.org/t/p/w500/${acteur.profile_path}"
                                    }),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        acteur.name,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavorisFilms(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.favMovies.collectAsState()
    val favMovies by viewModel.favMovies.collectAsState()
    viewModel.affichLastMovies()
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        if(movies.isEmpty()){
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Pas de films en favoris")
                }
            }
        }
        
        items(movies) { movie ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Log.i("ID FOCUSED", movie.id.toString())
                            navController.navigate("detail/${movie.id}")
                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${movie.fiche.poster_path}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(modifier = Modifier
                                    .size(50.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .background(Color(0xCC000000))
                                    .padding(5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = (movie.fiche.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center)
                                    CircularProgressIndicator(
                                        color = Color(0xFF001438),
                                        progress = 100f
                                    )
                                    CircularProgressIndicator(
                                        color = Color(
                                            red = 76,
                                            green = 175,
                                            blue = 80,
                                            alpha = 255
                                        ),
                                        progress = (movie.fiche.vote_average/10).toFloat()
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier.size(24.dp),
                                        onClick = {
                                            viewModel.deleteFavFilm(movie.fiche)
                                        }
                                    ) {
                                        if(movie.fiche.isFav || favMovies.any{isFavMovie ->
                                                isFavMovie.id == movie.id;
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        }else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        movie.fiche.title,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Acteurs(viewModel: MainViewModel) {
    val acteurs by viewModel.persons.collectAsState()
    val favActeurs by viewModel.favActeurs.collectAsState()

    if (acteurs.isEmpty()) viewModel.affichLastPersons()

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        items(acteurs) { acteur ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {

                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = (if(acteur.profile_path === null){
                                        "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png"
                                    }else{
                                        "https://image.tmdb.org/t/p/w500/${acteur.profile_path}"
                                    }),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier.size(24.dp),
                                        onClick = {
                                            if (acteur.isFav) {
                                                viewModel.deleteFavActeur(acteur)
                                            } else {
                                                viewModel.addFavActeur(acteur)
                                            }
                                        }
                                    ) {
                                        if (acteur.isFav || favActeurs.any { isFavActeur ->
                                                isFavActeur.id == acteur.id.toString();
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        } else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)

                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        acteur.name,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavorisActeurs(viewModel: MainViewModel, navController: NavController) {
    val acteurs by viewModel.favActeurs.collectAsState()
    val favActeurs by viewModel.favActeurs.collectAsState()
    viewModel.affichLastMovies()
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        if(acteurs.isEmpty()){
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Pas d'acteurs en favoris")
                }
            }
        }
        items(acteurs) { acteur ->
            Card(
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    Log.i("ID FOCUSED", acteur.id.toString())
                },
                modifier = Modifier
                    .focusable()
                    .padding(15.dp)
                    .clickable { },
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.height(200.dp)){
                        AsyncImage(
                            model = (if(acteur.fiche.profile_path === null){
                                "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png"
                            }else{
                                "https://image.tmdb.org/t/p/w500/${acteur.fiche.profile_path}"
                            }),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = {
                                    viewModel.deleteFavActeur(acteur.fiche)
                                }
                            ) {
                                if(acteur.fiche.isFav || favActeurs.any{isFavActeur ->
                                        isFavActeur.id == acteur.id;
                                    }) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        "favorite",
                                        tint = Color(0xFFCE1515)
                                    )
                                }else {
                                    Icon(
                                        Icons.Filled.FavoriteBorder,
                                        "favorite",
                                        tint = Color(0xFFCE1515)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ){
                            Text(
                                acteur.fiche.name,
                                fontWeight = FontWeight.W900,
                                color = Color(0xFFE0E0E0),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(color = Color(0xCC000000))
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                        }
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Series(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()
    val favSeries by viewModel.favSeries.collectAsState()
    viewModel.affichLastSeries()


    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        items(series) { serie ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Log.i("ID FOCUSED", serie.id.toString())
                            navController.navigate("detailTv/${serie.id}")
                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(modifier = Modifier
                                    .size(50.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .background(Color(0xCC000000))
                                    .padding(5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = (serie.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center)
                                    CircularProgressIndicator(
                                        color = Color(0xFF001438),
                                        progress = 100f
                                    )
                                    CircularProgressIndicator(
                                        color = Color(
                                            red = 76,
                                            green = 175,
                                            blue = 80,
                                            alpha = 255
                                        ),
                                        progress = (serie.vote_average/10).toFloat()
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier.size(24.dp),
                                        onClick = {
                                            if (serie.isFav) {
                                                viewModel.deleteFavSerie(serie)
                                            } else {
                                                viewModel.addFavSerie(serie)
                                            }
                                        }
                                    ) {
                                        if (serie.isFav || favSeries.any { isFavSerie ->
                                                isFavSerie.id == serie.id.toString();
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        } else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)

                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        serie.name,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavorisSeries(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.favSeries.collectAsState()
    val favSeries by viewModel.favSeries.collectAsState()
    viewModel.affichLastMovies()
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        if(series.isEmpty()){
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Pas de series en favoris")
                }
            }
        }
        items(series) { serie ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Log.i("ID FOCUSED", serie.id.toString())
                            navController.navigate("detail/${serie.id}")
                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${serie.fiche.poster_path}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(modifier = Modifier
                                    .size(50.dp)
                                    .clip(
                                        CircleShape
                                    )
                                    .background(Color(0xCC000000))
                                    .padding(5.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = (serie.fiche.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center)
                                    CircularProgressIndicator(
                                        color = Color(0xFF001438),
                                        progress = 100f
                                    )
                                    CircularProgressIndicator(
                                        color = Color(
                                            red = 76,
                                            green = 175,
                                            blue = 80,
                                            alpha = 255
                                        ),
                                        progress = (serie.fiche.vote_average/10).toFloat()
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier.size(24.dp),
                                        onClick = {
                                            viewModel.deleteFavSerie(serie.fiche)
                                        }
                                    ) {
                                        if(serie.fiche.isFav || favSeries.any{isFavMovie ->
                                                isFavMovie.id == serie.id;
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        }else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        serie.fiche.name,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailTv(viewModel: MainViewModel, idDetail: String) {
    val detail by viewModel.tvDetail.collectAsState()
    viewModel.affichDetailTv(idDetail)

    val credits by viewModel.tvCredits.collectAsState()
    viewModel.affichDetailTvCredits(idDetail)

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 120.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,60.dp)) {
        item(span = {(GridItemSpan(maxCurrentLineSpan))}){
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()){
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${detail.backdrop_path}",
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.width(500.dp)
                    )
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = detail.name,
                        style = MaterialTheme.typography.h3,
                    )
                    Text(
                        text = "Synopsis",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    )
                    Text(
                        text = detail.overview,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp)
                    )
                    Text(
                        text = "Tête d'affiche",
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
        items(credits.cast) { acteur ->
            (
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {

                        },
                        modifier = Modifier
                            .focusable()
                            .padding(15.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = (if(acteur.profile_path === null){
                                        "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png"
                                    }else{
                                        "https://image.tmdb.org/t/p/w500/${acteur.profile_path}"
                                    }),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomCenter
                                ){
                                    Text(
                                        acteur.name,
                                        fontWeight = FontWeight.W900,
                                        color = Color(0xFFE0E0E0),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .background(color = Color(0xCC000000))
                                            .width(200.dp)
                                            .padding(2.dp),
                                    )
                                }
                            }

                        }
                    })
        }
    }
}

