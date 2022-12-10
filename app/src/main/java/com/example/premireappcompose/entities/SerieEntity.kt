package com.example.premireappcompose.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premireappcompose.Serie

@Entity
data class SerieEntity(val fiche: Serie, @PrimaryKey val id: String)