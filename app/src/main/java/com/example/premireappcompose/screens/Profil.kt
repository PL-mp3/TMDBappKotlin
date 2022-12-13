package com.example.premireappcompose.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.premireappcompose.R


@Composable
fun Structure(windowClass: WindowSizeClass, navController: NavController){
    var navControl = navController
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Presentation()
                    Contact(navControl)
                }
            }
        }
        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically) {
                Presentation()

                Contact(navControl)
            }
        }
    }
}

@Composable
fun Presentation() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.width(200.dp)
        ) {
            Image(
                painterResource(R.drawable.photo),
                contentDescription = "PL",
                modifier = Modifier
                    .clip(RoundedCornerShape(1000.dp)),
            )
        }

        Text(
            "Pierre-Louis VERGÉLY", style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(10.dp)
        )
        Formation()
    }
}


@Composable
fun Formation() {
    Text("Étudiant licence pro DReAM")
}

@Composable
fun Contact(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Icon(Icons.Rounded.Call, contentDescription = "Tel")
            Text("Tel: 0633058155",modifier = Modifier.padding(10.dp))
        }
        Row() {
            Icon(Icons.Rounded.Email, contentDescription = "Email")
            Text("pierre-louis.vergely@etu.iut-tlse3.fr",modifier = Modifier.padding(10.dp))
        }
        SimpleButton(navController)
    }

}

@Composable
fun SimpleButton(navController: NavController) {
    val context = LocalContext.current
    val webIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vergely-pierrelouis.fr/"))
    Button(onClick = {
        context.startActivity(webIntent)
    },modifier = Modifier.padding(15.dp)) {
        Text(text = "Portfolio")
    }
    Button(onClick = { navController.navigate("films")
    },modifier = Modifier.padding(15.dp), colors = ButtonDefaults.buttonColors(
        backgroundColor = Color(
            red = 76,
            green = 175,
            blue = 80,
            alpha = 255
        ),
        contentColor = Color.White
    ), shape = RoundedCornerShape(50)
    )
    {
        Text(text = "TMDB App")
    }
}