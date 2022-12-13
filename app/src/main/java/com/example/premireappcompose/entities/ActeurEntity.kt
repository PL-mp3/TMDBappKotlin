package com.example.premireappcompose.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.premireappcompose.models.Person

@Entity
data class ActeurEntity(val fiche: Person, @PrimaryKey val id: String)