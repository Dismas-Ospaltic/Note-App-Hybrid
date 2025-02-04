package com.example.noteapphybrid.model

//data class Note(
//    val title: String, // The title of the note
//    val details: String, // The details or category of the note
//    val date: String // The date of the note
//)

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,   // Auto-generate ID
    val title: String,
    val details: String,
    val date: Long // Use timestamp to track when the note was created/updated
)

