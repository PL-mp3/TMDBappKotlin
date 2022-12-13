package com.example.premireappcompose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.premireappcompose.MainViewModel


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
