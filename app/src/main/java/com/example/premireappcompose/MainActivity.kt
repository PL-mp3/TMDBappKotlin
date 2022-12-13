package com.example.premireappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.premireappcompose.screens.*
import com.example.premireappcompose.ui.theme.PremièreAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val viewModel: MainViewModel by viewModels()


            PremièreAppComposeTheme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    topBar = {
                        var showSearch by remember { mutableStateOf(false)}
                        if((currentDestination !== null) && (currentDestination.route !== "profil")) {
                            if(showSearch){
                                when (currentDestination.route) {
                                    "movies" -> SearchView({it->viewModel.affichSearchMovies(it)}, { showSearch = !showSearch })
                                    "series" -> SearchView({it->viewModel.affichSearchTv(it)}, { showSearch = !showSearch })
                                    else -> {
                                        SearchView({it->viewModel.affichSearchMovies(it)}, { showSearch = !showSearch })
                                    }
                                }

                            }else{
                                DefaultTopBar( { showSearch = !showSearch }, navController,currentDestination)
                            };
                        }

                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NavHost(navController = navController, startDestination = "profil") {
                                composable("profil") { Structure(windowSizeClass, navController) }
                                composable("films") { Films(viewModel,navController) }
                                composable("series") { Series(viewModel,navController) }
                                composable("acteurs") { Acteurs(viewModel) }
                                composable("favorites/films") { FavorisFilms(viewModel,navController) }
                                composable("favorites/series") { FavorisSeries(viewModel,navController) }
                                composable("favorites/acteurs") { FavorisActeurs(viewModel,navController) }
                                composable("detail/{id}") {
                                        navBackStackEntry ->
                                    var idDetail = navBackStackEntry.arguments?.getString("id")
                                    DetailMovie(viewModel,idDetail.toString())
                                }
                                composable("detailTv/{id}") {
                                        navBackStackEntry ->
                                    var idDetail = navBackStackEntry.arguments?.getString("id")
                                    DetailTv(viewModel,idDetail.toString())
                                }
                            }
                        }
                    },
                    bottomBar = {
                        if(currentDestination !== null && currentDestination.route !== "profil") {
                        BottomNavigation {
                                items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                screen.icon,
                                                contentDescription = screen.label
                                            )
                                        },
                                        label = { Text(screen.label) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(onSearch: (t:String)->Unit, onToggleSearch: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var text by remember { mutableStateOf("")}


    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
            keyboardController?.hide()
            onSearch(text)
            onToggleSearch()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
        .onFocusChanged {
            if (it.isFocused) {
                keyboardController?.show()
            }
        },
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            IconButton(onClick = { onToggleSearch()
            },
                modifier = Modifier.padding(1.dp,0.dp,0.dp,0.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        trailingIcon = {
            if (text != "") {
                IconButton(
                    onClick = {
                        text = ""
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = colorResource(id = R.color.purple_500),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultTopBar(onToggleSearch: () -> Unit, navController:NavController, currentDestination: NavDestination?) {
    TopAppBar(
        title = {
            Text(text = "TMDB App")
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        actions = {
            if (currentDestination != null) {
                if (currentDestination.route != "detailTv/{id}" && currentDestination.route != "detail/{id}" && currentDestination.route != "favorites/films" && currentDestination.route != "favorites/series" && currentDestination.route != "favorites/acteurs") {
                    IconButton(onClick = { onToggleSearch() }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate("favorites/${currentDestination.route}")
                    }

                    ) {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = "favorite page",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            }
        },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White,
        elevation = 10.dp,
    )
}