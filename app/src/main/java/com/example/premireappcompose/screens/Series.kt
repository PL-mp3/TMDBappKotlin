package com.example.premireappcompose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.premireappcompose.MainViewModel
import kotlin.math.roundToInt


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
                                    Text(text = (serie.fiche.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center,color = Color.White)
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
                                    Text(text = (serie.vote_average * 10.0).roundToInt().toString()+" %",fontSize = 10.sp, textAlign = TextAlign.Center,color = Color.White)
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

