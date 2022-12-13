package com.example.premireappcompose.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premireappcompose.models.Movie

@Entity
data class FilmEntity(val fiche: Movie, @PrimaryKey val id: String)